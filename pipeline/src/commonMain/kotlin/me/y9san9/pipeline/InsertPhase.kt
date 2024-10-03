package me.y9san9.pipeline

import me.y9san9.pipeline.phase.*
import me.y9san9.pipeline.phase.name

public fun PipelineBuilder.insertPhaseAfter(
    which: PipelinePhase,
    block: PipelinePhaseBuilder.() -> Unit
) {
    insertPhaseAfter(which, buildPipelinePhase(block = block))
}

public inline fun PipelineBuilder.insertPhaseAfter(
    which: PipelinePhase,
    phase: PipelinePhase
) {
    val phases = this.phases.toMutableList()

    for (i in phases.indices) {
        if (phases[i].name != which.name) continue
        phases.add(index = i + 1, phase)
        this.phases = phases
        return
    }

    error("No phase named '${which.name}'")
}

public fun PipelineBuilder.insertPhaseBefore(
    which: PipelinePhase,
    block: PipelinePhaseBuilder.() -> Unit
) {
    insertPhaseBefore(which, buildPipelinePhase(block = block))
}

public inline fun PipelineBuilder.insertPhaseBefore(
    which: PipelinePhase,
    phase: PipelinePhase
) {
    val phases = this.phases.toMutableList()

    for (i in phases.indices) {
        if (phases[i].name != which.name) continue
        phases.add(index = i, phase)
        this.phases = phases
        return
    }

    error("No phase named '${which.name}'")
}

public fun PipelineBuilder.insertPhaseFirst(block: PipelinePhaseBuilder.() -> Unit) {
    insertPhaseFirst(buildPipelinePhase(block = block))
}

public fun PipelineBuilder.insertPhaseFirst(phase: PipelinePhase) {
    val phases = this.phases.toMutableList()
    phases.add(index = 0, phase)
    this.phases = phases
}

public fun PipelineBuilder.insertPhaseLast(block: PipelinePhaseBuilder.() -> Unit) {
    insertPhaseLast(buildPipelinePhase(block = block))
}

public fun PipelineBuilder.insertPhaseLast(phase: PipelinePhase) {
    val phases = this.phases.toMutableList()
    phases.add(phase)
    this.phases = phases
}
