package me.y9san9.ksm.telegram.callbackQuery.state

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.edit.text.editMessageText
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.inline_message_id
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.message
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.InlineMessageId
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.inlineMessageIdField
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import me.y9san9.ksm.telegram.base.TelegramFSMBase
import me.y9san9.ksm.telegram.handler.base.TelegramUpdateHandlerBase.Subject
import me.y9san9.ksm.telegram.state.UpdateTransition
import me.y9san9.ksm.telegram.state.base.UpdateStateBase
import me.y9san9.ksm.telegram.state.routing.StateRouter
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set

public object CallbackQueryTransition {
    @PipelineDsl
    public class Scope(public val context: PipelineContext) {
        @PipelineDsl
        public val bot: TelegramBot
            get() = context.require(Subject.Bot)

        @PipelineDsl
        public val router: StateRouter
            get() = StateRouter(context)

        /**
         * If [inlineMessageId] is null, [messageId] and [chatId] is not null
         */
        @PipelineDsl
        public val chatId: ChatIdentifier?
            get() = context[Subject.CallbackQueryChatId]

        /**
         * If [inlineMessageId] is null, [messageId] and [chatId] is not null
         */
        @PipelineDsl
        public val messageId: MessageId?
            get() = context[Subject.CallbackQueryMessageId]

        /**
         * If [inlineMessageId] is null, [messageId] and [chatId] is not null
         */
        @PipelineDsl
        public val inlineMessageId: InlineMessageId?
            get() = context[Subject.CallbackQueryInlineMessageId]

        @PipelineDsl
        public suspend fun editMessage(
            entities: TextSourcesList,
            replyMarkup: InlineKeyboardMarkup? = null
        ) {
            val messageId = messageId
            val chatId = chatId
            val inlineMessageId = inlineMessageId

            if (messageId != null && chatId != null) {
                bot.editMessageText(
                    chatId = chatId,
                    messageId = messageId,
                    entities = entities,
                    replyMarkup = replyMarkup
                )
            }
            if (inlineMessageId != null) {
                bot.editMessageText(
                    inlineMessageId = inlineMessageId,
                    entities = entities,
                    replyMarkup = replyMarkup
                )
            }
        }
    }
}

public fun CallbackQueryState.Builder.transition(block: suspend CallbackQueryTransition.Scope.() -> Unit) {
    context[UpdateStateBase.Config.Transition] = UpdateTransition { scope ->
        CallbackQueryTransition.Scope(scope.context).block()
    }
}
