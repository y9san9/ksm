package me.y9san9.ksm.telegram.callbackQuery.state

import me.y9san9.ksm.telegram.state.UpdateHandler
import me.y9san9.ksm.telegram.state.base.UpdateStateBase
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.set

public object CallbackQueryHandler {
    @PipelineDsl
    public open class Scope(context: PipelineContext) : UpdateHandler.Scope(context)
}

@PipelineDsl
public fun CallbackQueryState.Builder.handle(block: suspend CallbackQueryHandler.Scope.() -> Unit) {
    context[UpdateStateBase.Config.Handler] = UpdateHandler { scope ->
        CallbackQueryHandler.Scope(scope.context).block()
    }
}
