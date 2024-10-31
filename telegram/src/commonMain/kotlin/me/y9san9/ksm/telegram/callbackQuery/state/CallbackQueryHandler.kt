package me.y9san9.ksm.telegram.callbackQuery.state

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.data
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.inline_message_id
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.message
import dev.inmo.tgbotapi.types.InlineMessageId
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.MessageContent
import dev.inmo.tgbotapi.types.update.CallbackQueryUpdate
import dev.inmo.tgbotapi.utils.RiskFeature
import me.y9san9.ksm.telegram.handler.base.TelegramUpdateHandlerBase.Subject
import me.y9san9.ksm.telegram.state.UpdateHandler
import me.y9san9.ksm.telegram.state.base.UpdateStateBase
import me.y9san9.ksm.telegram.state.routing.StateRouter
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set

public object CallbackQueryHandler {
    @PipelineDsl
    public open class Scope(public val context: PipelineContext) {
        @PipelineDsl
        public val bot: TelegramBot
            get() = context.require(Subject.Bot)

        @PipelineDsl
        public val router: StateRouter
            get() = StateRouter(context)

        @PipelineDsl
        public val update: CallbackQueryUpdate
            get() = context.require(Subject.CallbackQueryUpdate)

        /**
         * If [transition] is null, [inlineMessageId] is not null
         */
        @OptIn(RiskFeature::class)
        @PipelineDsl
        public val message: ContentMessage<MessageContent>?
            get() = update.data.message

        /**
         * If [inlineMessageId] is null, [transition] is not null
         */
        @PipelineDsl
        public val inlineMessageId: InlineMessageId?
            get() = context[Subject.CallbackQueryInlineMessageId]

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

@PipelineDsl
public fun CallbackQueryState.Builder.handle(block: suspend CallbackQueryHandler.Scope.() -> Unit) {
    context[UpdateStateBase.Config.Handler] = UpdateHandler { scope ->
        CallbackQueryHandler.Scope(scope.context).block()
    }
}
