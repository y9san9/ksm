package me.y9san9.ksm.telegram.privateMessage.state

import me.y9san9.ksm.telegram.state.UpdateTransition
import me.y9san9.ksm.telegram.state.base.UpdateStateBase.Config
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.set

@PipelineDsl
public fun PrivateMessageState.Builder.transition(block: suspend PrivateMessageHandler.Scope.() -> Unit) {
    context[Config.Transition] = UpdateTransition { scope ->
        PrivateMessageHandler.Scope(scope.context).block()
    }
}
