package me.y9san9.ksm.telegram.privateMessage.state

import me.y9san9.ksm.telegram.state.StateName
import me.y9san9.ksm.telegram.state.StateHandler
import me.y9san9.ksm.telegram.state.State
import me.y9san9.ksm.telegram.state.StateTransition
import me.y9san9.pipeline.annotation.PipelineDsl

public object PrivateMessageState {
    @PipelineDsl
    public class Builder {
        public val update: State.Builder = State.Builder()

        @PipelineDsl
        public var name: StateName? by update::name

        @PipelineDsl
        public fun handle(block: suspend PrivateMessageHandler.Scope.() -> Unit) {
            update.handler = StateHandler { scope ->
                PrivateMessageHandler.Scope(scope.context).block()
            }
        }

        @PipelineDsl
        public fun transition(block: suspend PrivateMessageTransition.Scope.() -> Unit) {
            update.transition = StateTransition { scope ->
                PrivateMessageTransition.Scope(scope.context).block()
            }
        }
    }
}
