package me.y9san9.ksm.telegram.callbackQuery.state

import dev.inmo.tgbotapi.types.MessageId
import me.y9san9.ksm.telegram.state.StateName
import me.y9san9.ksm.telegram.state.StateHandler
import me.y9san9.ksm.telegram.state.State
import me.y9san9.ksm.telegram.state.StateTransition
import me.y9san9.pipeline.annotation.PipelineDsl

public object CallbackQueryState {
    public class Builder {
        public val update: State.Builder = State.Builder()

        @PipelineDsl
        public var name: StateName? by update::name

        @PipelineDsl
        public fun handle(block: suspend CallbackQueryHandler.Scope.() -> Unit) {
            update.handler = StateHandler { scope ->
                CallbackQueryHandler.Scope(scope.context).block()
            }
        }

        @PipelineDsl
        public fun transition(block: suspend CallbackQueryTransition.Scope.() -> Unit) {
            update.transition = StateTransition { scope ->
                CallbackQueryTransition.Scope(scope.context).block()
            }
        }

        @PipelineDsl
        public fun initial(block: suspend CallbackQueryInitial.Scope.() -> MessageId) {
            update.name
        }
    }
}
