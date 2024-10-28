package me.y9san9.ksm.telegram.callbackQuery.state

import dev.inmo.tgbotapi.extensions.api.edit.text.editMessageText
import dev.inmo.tgbotapi.extensions.api.send.media.sendMediaGroup
import dev.inmo.tgbotapi.extensions.api.send.sendMessage
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.inline_message_id
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.message
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.RawChatId
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.TextContent
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.replyMarkupField
import dev.inmo.tgbotapi.types.update.CallbackQueryUpdate
import dev.inmo.tgbotapi.types.update.MessageUpdate
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.buildEntities
import io.ktor.http.content.*
import me.y9san9.ksm.telegram.state.UpdateHandler
import me.y9san9.ksm.telegram.state.UpdateTransition
import me.y9san9.ksm.telegram.state.base.UpdateStateBase
import me.y9san9.ksm.telegram.state.bot
import me.y9san9.ksm.telegram.state.update
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.set

public object CallbackQueryMessage {
    @PipelineDsl
    public class Scope(context: PipelineContext) : UpdateHandler.Scope(context) {

        @PipelineDsl
        public var entities: TextSourcesList? = null

        @PipelineDsl
        public var text: String
            @Deprecated(message = "use setter", level = DeprecationLevel.HIDDEN) get() = TODO("use setter")
            set(value) { entities = buildEntities { +value } }

        @PipelineDsl
        public var keyboard: InlineKeyboardMarkup? = null
    }
}

// todo: support external and internal transitions
@OptIn(RiskFeature::class)
@PipelineDsl
public inline fun CallbackQueryState.Builder.message(crossinline block: CallbackQueryMessage.Scope.() -> Unit) {

    context[UpdateStateBase.Config.Transition] = UpdateTransition { scope ->
        val messageScope = CallbackQueryMessage.Scope(scope.context)
        messageScope.apply(block)

        val entities = messageScope.entities ?: error("Please set up `entities` or `text`")
        // todo: delete state from db if keyboard is null
        val keyboard = messageScope.keyboard

        val bot = scope.bot
        val update = scope.update

        if (update is CallbackQueryUpdate) {
            val inlineMessageId = update.data.inline_message_id
            if (inlineMessageId != null) {
                bot.editMessageText(
                    inlineMessageId = inlineMessageId,
                    entities = entities,
                    replyMarkup = keyboard
                )
            }
            @Suppress("UNCHECKED_CAST")
            val message = update.data.message as ContentMessage<TextContent>?
            if (message != null) {
                bot.editMessageText(
                    message = message,
                    entities = entities,
                    replyMarkup = keyboard
                )
            }
        } else if (update is MessageUpdate) { // todo: move to external transition
            bot.sendMessage(
                chat = update.data.chat,
                entities = entities,
                replyMarkup = keyboard
            )
        }
    }
}
