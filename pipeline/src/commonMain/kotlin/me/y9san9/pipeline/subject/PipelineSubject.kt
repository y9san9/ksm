package me.y9san9.pipeline.subject

import me.y9san9.pipeline.context.*

/**
 * Subjects are required to support invocation of Pipelines from Pipelines.
 * Unlike usual Pipeline.context which is being different for each Pipeline,
 * subject is passed inside the pipeline.
 */
public object PipelineSubject : PipelineElement<PipelineContext>

public val PipelineContext.subject: PipelineContext
    get() = require(PipelineSubject) { "Subject is not present on current context" }

public fun <T : Any> MutablePipelineContext.setSubject(
    element: PipelineElement<T>,
    value: T?
) {
    subject = buildPipelineContext(subject) { this[element] = value }
}

public fun <T : Any> MutablePipelineContext.setSubject(
    element: PipelineElement<T>,
    from: PipelineElement<T>
) {
    setSubject(element, subject[from])
}

public var MutablePipelineContext.subject: PipelineContext
    get() = this[PipelineSubject] ?: PipelineContext.Empty
    set(value) {
        if (value is MutablePipelineContext) error("PipelineSubject must be immutable")
        this[PipelineSubject] = value
    }
