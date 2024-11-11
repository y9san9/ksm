package me.y9san9.ksm.telegram.routing

import me.y9san9.ksm.telegram.TelegramFSM.Plugin.SelectedState
import me.y9san9.ksm.telegram.TelegramFSM.Plugin.StateDescriptor
import me.y9san9.ksm.telegram.TelegramFSM.Plugin.StateGroup
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase

public val FindByNamePhase: PipelinePhase = buildPipelinePhase {
    name = "FindByNamePhase"

    runnable {
        val descriptor = context[StateDescriptor]
        val group = require(StateGroup)
        val stateList = group.stateList
        context[SelectedState] = stateList[descriptor]
    }
}
