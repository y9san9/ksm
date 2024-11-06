package me.y9san9.ksm.telegram.callbackQuery.state

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.edit.text.editMessageText
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.InlineMessageId
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import me.y9san9.ksm.telegram.plugin.handler.callbackQuery.CallbackQueryPlugin.ChatId
import me.y9san9.ksm.telegram.plugin.handler.callbackQuery.CallbackQueryPlugin.InlineMessageId
import me.y9san9.ksm.telegram.plugin.handler.callbackQuery.CallbackQueryPlugin.MessageId
import me.y9san9.ksm.telegram.plugin.handler.HandlerPlugin.Bot
import me.y9san9.ksm.telegram.state.routing.StateRouter
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require

public object CallbackQueryTransition {
    @PipelineDsl
    public class Scope(public val context: PipelineContext) {

        @PipelineDsl
        public val router: StateRouter = StateRouter(context)

        @PipelineDsl
        public val bot: TelegramBot
            get() = context.require(Bot)

        /**
         * If [inlineMessageId] is null, [messageId] and [chatId] is not null
         */
        @PipelineDsl
        public val chatId: ChatIdentifier?
            get() = context[ChatId]

        /**
         * If [inlineMessageId] is null, [messageId] and [chatId] is not null
         */
        @PipelineDsl
        public val messageId: MessageId?
            get() = context[MessageId]

        /**
         * If [inlineMessageId] is null, [messageId] and [chatId] is not null
         */
        @PipelineDsl
        public val inlineMessageId: InlineMessageId?
            get() = context[InlineMessageId]

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
