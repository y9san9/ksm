package me.y9san9.pipeline

import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.mutablePipelineContextOf
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.plugin.PipelinePlugin
import me.y9san9.pipeline.plugin.install

public data class Pipeline(public val context: PipelineContext) {
    public val phases: List<PipelinePhase> get() = context.require(PhaseList)

    public class Builder(context: PipelineContext) {
        public val context: MutablePipelineContext = mutablePipelineContextOf(context)

        public var phases: List<PipelinePhase>
            get() = context.require(PhaseList)
            set(value) { context[PhaseList] = value }

        public constructor() : this(PipelineContext.Empty) {
            context.install(Pipeline)
        }

        public fun build(): Pipeline = Pipeline(context.toPipelineContext())
    }

    public companion object Plugin : PipelinePlugin {
        override val name: String = "Pipeline"

        public val PhaseList: PipelineElement<List<PipelinePhase>> by PipelineElement
        public val Subject: PipelineElement<PipelineContext> by PipelineElement

        override fun apply(context: MutablePipelineContext) {
            context[PhaseList] = emptyList()
            context[Subject] = PipelineContext.Empty
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
