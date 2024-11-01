package me.y9san9.ksm.telegram.callbackQuery.state

import me.y9san9.ksm.telegram.state.StateName
import me.y9san9.ksm.telegram.state.UpdateHandler
import me.y9san9.ksm.telegram.state.UpdateState
import me.y9san9.ksm.telegram.state.UpdateTransition
import me.y9san9.pipeline.annotation.PipelineDsl

public object CallbackQueryState {
    public class Builder {
        public val update: UpdateState.Builder = UpdateState.Builder()

        @PipelineDsl
        public var name: StateName? by update::name

        @PipelineDsl
        public fun handle(block: suspend CallbackQueryHandler.Scope.() -> Unit) {
            update.handler = UpdateHandler { scope ->
                CallbackQueryHandler.Scope(scope.context).block()
            }
        }

        @PipelineDsl
        public fun transition(block: suspend CallbackQueryTransition.Scope.() -> Unit) {
            update.transition = UpdateTransition { scope ->
                CallbackQueryTransition.Scope(scope.context).block()
            }
        }
    }
}
