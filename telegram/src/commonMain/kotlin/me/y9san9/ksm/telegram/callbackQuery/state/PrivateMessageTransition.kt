package me.y9san9.ksm.telegram.callbackQuery.state

import me.y9san9.ksm.telegram.state.UpdateTransition
import me.y9san9.ksm.telegram.state.base.UpdateStateBase.Config
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.set

@PipelineDsl
public fun CallbackQueryState.Builder.transition(block: suspend CallbackQueryHandler.Scope.() -> Unit) {
    context[Config.Transition] = UpdateTransition { scope ->
        CallbackQueryHandler.Scope(scope.context).block()
    }
}
