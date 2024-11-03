package me.y9san9.pipeline

import me.y9san9.pipeline.base.PipelineBase
import me.y9san9.pipeline.context.*

public val Pipeline.subject: PipelineContext
    get() = context.require(PipelineBase.Subject)

public val Pipeline.Builder.subject: PipelineContext
    get() = context.require(PipelineBase.Subject)

public fun <T : Any> Pipeline.Builder.setSubject(
    element: PipelineElement<T>,
    value: T
) {
    context[PipelineBase.Subject] = context[PipelineBase.Subject].build { context[element] = value }
}
