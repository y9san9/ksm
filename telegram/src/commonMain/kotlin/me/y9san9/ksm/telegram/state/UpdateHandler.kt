package me.y9san9.ksm.telegram.state

import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.PipelineContext

public fun interface UpdateHandler {
    public suspend fun run(scope: Scope)

    @PipelineDsl
    public open class Scope(public val context: PipelineContext)
}
