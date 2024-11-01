package me.y9san9.ksm.telegram.privateMessage.state

import me.y9san9.ksm.telegram.state.StateName
import me.y9san9.ksm.telegram.state.UpdateHandler
import me.y9san9.ksm.telegram.state.UpdateState
import me.y9san9.ksm.telegram.state.UpdateTransition
import me.y9san9.pipeline.annotation.PipelineDsl

public object PrivateMessageState {
    public class Builder {
        public val update: UpdateState.Builder = UpdateState.Builder()

        @PipelineDsl
        public var name: StateName? by update::name

        @PipelineDsl
        public fun handle(block: suspend PrivateMessageHandler.Scope.() -> Unit) {
            update.handler = UpdateHandler { scope ->
                PrivateMessageHandler.Scope(scope.context).block()
            }
        }

        @PipelineDsl
        public fun transition(block: suspend PrivateMessageTransition.Scope.() -> Unit) {
            update.transition = UpdateTransition { scope ->
                PrivateMessageTransition.Scope(scope.context).block()
            }
        }
    }
}
