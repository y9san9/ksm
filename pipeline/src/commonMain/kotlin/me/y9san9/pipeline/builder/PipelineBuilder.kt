package me.y9san9.pipeline.builder

import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.plugin.PipelinePlugin

public class PipelineBuilder(
    public val phases: MutableList<PipelinePhase> = mutableListOf(PipelinePhase.Start, PipelinePhase.Finish),
    public var context: PipelineContext = PipelineContext.Empty
) {
    public inline fun install(plugin: PipelinePlugin) {
        val existing = context[plugin]
        if (existing != null) {
            error("Cannot install plugin '$plugin' twice")
        }
        plugin.apply(builder = this)
        context = context.with(plugin)
    }

    public operator fun contains(name: String): Boolean {
        return phases.any { phase -> phase.name == name }
    }

    public fun insertPhaseBefore(name: String, phase: PipelinePhase) {
        if (name == PipelinePhase.Start.name) {
            error("Cannot insert phase before Start")
        }
        val phaseName = phase.name
        if (phaseName != null && phaseName in this) {
            error("Phase '$name' is already present on pipeline")
        }
        for (i in phases.indices) {
            val element = phases[i]
            if (element.name == name) {
                phases.add(index = i, phase)
                return
            }
        }
        error("Cannot find phase named '$name'")
    }

    public fun insertPhaseAfter(name: String, phase: PipelinePhase) {
        if (name == PipelinePhase.Finish.name) error("Cannot insert phase after Finish")
        val phaseName = phase.name
        if (phaseName != null && phaseName in this) {
            error("Phase '$phaseName' is already present on pipeline")
        }
        for (i in phases.indices) {
            val element = phases[i]
            if (element.name == name) {
                phases.add(index = i + 1, phase)
                return
            }
        }
        error("Cannot find phase named '$name'")
    }

    public fun build(): Pipeline {
        return Pipeline(context, phases.toList())
    }

}
