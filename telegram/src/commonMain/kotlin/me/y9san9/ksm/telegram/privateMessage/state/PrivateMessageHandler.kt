package me.y9san9.ksm.telegram.privateMessage.state

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.types.update.MessageUpdate
import me.y9san9.ksm.telegram.handler.base.TelegramUpdateHandlerBase.Subject
import me.y9san9.ksm.telegram.state.UpdateHandler
import me.y9san9.ksm.telegram.state.base.UpdateStateBase
import me.y9san9.ksm.telegram.state.routing.StateRouter
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set

public object PrivateMessageHandler {
    @PipelineDsl
    public class Scope(public val context: PipelineContext) {
        @PipelineDsl
        public val router: StateRouter = StateRouter(context)

        @PipelineDsl
        public val bot: TelegramBot
            get() = context.require(Subject.Bot)

        @PipelineDsl
        public val update: MessageUpdate
            get() = context.require(Subject.PrivateMessageUpdate)

        @PipelineDsl
        public val message: PrivateContentMessage<*>
            get() = context.require(Subject.PrivateMessageData)

        @PipelineDsl
        public val user: User
            get() = message.from
    }
}
