package me.y9san9.pipeline

import me.y9san9.pipeline.base.PipelineBase
import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.plugin.install

public data class Pipeline(public val context: PipelineContext) {
    public class Builder(context: PipelineContext) {
        public val context: MutablePipelineContext = mutablePipelineContextOf(context)

        public constructor() : this(PipelineContext.Empty) {
            context.install(PipelineBase)
        }

        public fun build(): Pipeline {
            return Pipeline(context.toPipelineContext())
        }
    }
}

public inline fun buildPipeline(
    base: Pipeline? = null,
    block: Pipeline.Builder.() -> Unit = {}
): Pipeline {
    val builder = when (base) {
        null -> Pipeline.Builder()
        else -> Pipeline.Builder(base.context)
    }
    builder.block()
    return builder.build()
}

public inline fun MutablePipelineContext.set(
    element: PipelineElement<Pipeline>,
    block: Pipeline.Builder.() -> Unit
) {
    context[element] = buildPipeline(context[element], block)
}
