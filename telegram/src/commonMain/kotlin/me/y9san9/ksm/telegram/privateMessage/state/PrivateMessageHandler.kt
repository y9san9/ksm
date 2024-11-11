package me.y9san9.ksm.telegram.privateMessage.state

import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.types.update.MessageUpdate
import me.y9san9.ksm.telegram.privateMessage.PrivateMessagePlugin.Message
import me.y9san9.ksm.telegram.privateMessage.PrivateMessagePlugin.Update
import me.y9san9.ksm.telegram.state.StateHandler
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require

public object PrivateMessageHandler {
    @PipelineDsl
    public class Scope(override val context: PipelineContext) : StateHandler.Scope {
        @PipelineDsl
        override val update: MessageUpdate get() = context.require(Update)

        @PipelineDsl
        public val message: PrivateContentMessage<*> get() = context.require(Message)

        @PipelineDsl
        public val user: User get() = message.from
    }
}
