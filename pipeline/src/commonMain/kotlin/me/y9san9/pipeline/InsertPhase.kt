package me.y9san9.pipeline

import me.y9san9.pipeline.phase.*

public fun Pipeline.Builder.insertPhaseAfter(
    which: PipelinePhase,
    block: PipelinePhase.Builder.() -> Unit
) {
    insertPhaseAfter(which, buildPipelinePhase(block = block))
}

public fun Pipeline.Builder.insertPhaseAfter(
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

public fun Pipeline.Builder.insertPhaseBefore(
    which: PipelinePhase,
    block: PipelinePhase.Builder.() -> Unit
) {
    insertPhaseBefore(which, buildPipelinePhase(block = block))
}

public fun Pipeline.Builder.insertPhaseBefore(
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

public fun Pipeline.Builder.insertPhaseFirst(block: PipelinePhase.Builder.() -> Unit) {
    insertPhaseFirst(buildPipelinePhase(block = block))
}

public fun Pipeline.Builder.insertPhaseFirst(phase: PipelinePhase) {
    val phases = this.phases.toMutableList()
    phases.add(index = 0, phase)
    this.phases = phases
}

public fun Pipeline.Builder.insertPhaseLast(block: PipelinePhase.Builder.() -> Unit) {
    insertPhaseLast(buildPipelinePhase(block = block))
}

public fun Pipeline.Builder.insertPhaseLast(phase: PipelinePhase) {
    val phases = this.phases.toMutableList()
    phases.add(phase)
    this.phases = phases
}
