package me.y9san9.pipeline

import me.y9san9.pipeline.builder.PipelineBuilder
import me.y9san9.pipeline.builder.buildPipeline
import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.phase.PipelinePhase

public class Pipeline(
    public val context: PipelineContext,
    public val phases: List<PipelinePhase> = listOf(PipelinePhase.Start, PipelinePhase.Finish)
) {
    init {
        require(phases.first() == PipelinePhase.Start)
        require(phases.last() == PipelinePhase.Finish)
    }

    public operator fun contains(name: String): Boolean {
        return phases.any { phase -> phase.name == name }
    }

    public inline fun build(block: PipelineBuilder.() -> Unit): Pipeline {
        return buildPipeline(base = this, block = block)
    }

    public suspend fun proceed(context: PipelineContext): PipelineContext {
        var acc = this.context + context

        for (phase in phases) {
            acc = phase.proceed(acc)
            if (PipelineSignal.Return in acc) {
                return acc.remove(PipelineSignal.Return)
            }
            if (acc.isFailure) return acc
        }
        return acc
    }

    public fun prettyString(): String {
        return "Pipeline[${phases.joinToString(separator = " -> ") { it.name ?: "$it" }}]"
    }
}
