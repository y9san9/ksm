package me.y9san9.ksm.telegram.plugin

import me.y9san9.ksm.telegram.group.base.UpdateStateGroupBase
import me.y9san9.ksm.telegram.plugin.TelegramPlugin.StateGroupList
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable

public val SetupPhase: PipelinePhase = buildPipelinePhase {
    name = "SetupPhase"

    runnable {
        for (group in context.require(StateGroupList)) {
            group.context.require(UpdateStateGroupBase.Storage) { "Please provide 'storage' for every StateGroup" }
        }
    }
}
