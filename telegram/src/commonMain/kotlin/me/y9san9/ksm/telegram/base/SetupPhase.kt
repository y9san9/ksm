package me.y9san9.ksm.telegram.base

import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable

public val SetupPhase: PipelinePhase = buildPipelinePhase {
    name = "SetupPhase"

    runnable {
        require(TelegramFSMBase.Subject.StateList) { "Please setup `routing` in buildTelegramFSM" }
        require(TelegramFSMBase.Subject.Storage) { "Please setup `storage` in buildTelegramFSM" }
    }
}
