package me.y9san9.ksm.telegram.state

import me.y9san9.ksm.telegram.state.base.StateBase
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.set

public fun interface StateHandler {
    public suspend fun run(scope: Scope)

    @PipelineDsl
    public class Scope(public val context: PipelineContext)
}

@PipelineDsl
public fun State.Builder.handle(block: suspend StateHandler.Scope.() -> Unit) {
    context[StateBase.Config.Handler] = StateHandler(block)
}
