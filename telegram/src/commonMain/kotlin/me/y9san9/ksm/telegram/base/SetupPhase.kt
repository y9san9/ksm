package me.y9san9.ksm.telegram.base

import me.y9san9.ksm.telegram.base.TelegramFSMBase.Subject
import me.y9san9.ksm.telegram.group.StateGroup
import me.y9san9.ksm.telegram.group.base.StateGroupBase
import me.y9san9.ksm.telegram.group.storage
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable

public val SetupPhase: PipelinePhase = buildPipelinePhase {
    name = "SetupPhase"

    runnable {
        for (group in context.require(Subject.StateGroups)) {
            group.context.require(StateGroupBase.Config.Storage) { "Please provide 'storage' for every StateGroup" }
        }
    }
}
