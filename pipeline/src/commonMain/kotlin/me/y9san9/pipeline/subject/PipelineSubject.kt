package me.y9san9.pipeline.subject

import me.y9san9.pipeline.context.*

public object PipelineSubject : PipelineElement<PipelineContext>

public val PipelineContext.subject: PipelineContext
    get() = require(PipelineSubject) { "Subject is not present on current context" }

public inline fun MutablePipelineContext.subject(
    block: MutablePipelineContext.() -> Unit
) {
    subject = buildPipelineContext(subject, block)
}

public var MutablePipelineContext.subject: PipelineContext
    get() = require(PipelineSubject) { "Subject is not present on current context" }
    set(value) {
        if (value is MutablePipelineContext) error("PipelineSubject must be immutable")
        this[PipelineSubject] = value
    }
