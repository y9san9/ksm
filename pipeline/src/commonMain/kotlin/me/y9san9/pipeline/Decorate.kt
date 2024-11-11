package me.y9san9.pipeline

import me.y9san9.pipeline.Pipeline.Plugin.PhaseList
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase

public inline fun Pipeline.Builder.decorate(
    phase: PipelinePhase,
    decorator: PipelinePhase.Builder.(phase: PipelinePhase) -> Unit
) {
    val phases = buildList {
        for (currentPhase in context.require(PhaseList)) {
            if (currentPhase.name == phase.name) {
                val decoratedPhase = buildPipelinePhase(currentPhase) {
                    decorator(currentPhase)
                }
                add(decoratedPhase)
            } else {
                add(currentPhase)
            }
        }
    }
    this.phases = phases
}
