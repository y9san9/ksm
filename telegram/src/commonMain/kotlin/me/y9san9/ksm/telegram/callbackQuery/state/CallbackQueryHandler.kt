package me.y9san9.ksm.telegram.callbackQuery.state

import dev.inmo.tgbotapi.extensions.utils.extensions.raw.data
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.message
import dev.inmo.tgbotapi.types.InlineMessageId
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.MessageContent
import dev.inmo.tgbotapi.types.update.CallbackQueryUpdate
import dev.inmo.tgbotapi.utils.RiskFeature
import me.y9san9.ksm.telegram.callbackQuery.CallbackQueryPlugin.InlineMessageId
import me.y9san9.ksm.telegram.callbackQuery.CallbackQueryPlugin.Update
import me.y9san9.ksm.telegram.state.StateHandler
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require

public object CallbackQueryHandler {
    @PipelineDsl
    public class Scope(override val context: PipelineContext) : StateHandler.Scope {
        @PipelineDsl
        override val update: CallbackQueryUpdate
            get() = context.require(Update)

        /**
         * If [message] is null, [inlineMessageId] is not null
         */
        @OptIn(RiskFeature::class)
        @PipelineDsl
        public val message: ContentMessage<MessageContent>?
            get() = update.data.message

        /**
         * If [inlineMessageId] is null, [message] is not null
         */
        @PipelineDsl
        public val inlineMessageId: InlineMessageId?
            get() = context[InlineMessageId]

        @PipelineDsl
        public val user: User
            get() = update.data.from

        @PipelineDsl
        @OptIn(RiskFeature::class)
        public inline fun case(data: CallbackData, block: () -> Unit) {
            if (update.data.data == data.string) {
                block()
            }
        }
    }
}
