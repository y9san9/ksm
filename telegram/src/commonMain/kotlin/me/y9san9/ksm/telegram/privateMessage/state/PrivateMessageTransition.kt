package me.y9san9.ksm.telegram.privateMessage.state

import dev.inmo.tgbotapi.types.UserId
import me.y9san9.ksm.telegram.privateMessage.PrivateMessagePlugin.UserId
import me.y9san9.ksm.telegram.state.StateTransition
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require

// todo: that transition should not be called when using sendInlineFSM
public object PrivateMessageTransition {

    @PipelineDsl
    public class Scope(override val context: PipelineContext) : StateTransition.Scope {

        @PipelineDsl
        public val userId: UserId get() = context.require(UserId)
    }
}
