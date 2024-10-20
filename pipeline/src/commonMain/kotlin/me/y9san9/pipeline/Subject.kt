package me.y9san9.pipeline

import me.y9san9.pipeline.base.PipelineBase.Config
import me.y9san9.pipeline.context.*

public val PipelineContext.subject: PipelineContext
    get() = context[Config.Subject] ?: PipelineContext.Empty

public var MutablePipelineContext.subject: PipelineContext
    get() = context[Config.Subject] ?: PipelineContext.Empty
    set(value) { context[Config.Subject] = value }

public inline fun <T : Any> MutablePipelineContext.setSubject(
    element: PipelineElement<T>,
    value: T?
) {
    context[Config.Subject] = subject.build { set(element, value) }
}
