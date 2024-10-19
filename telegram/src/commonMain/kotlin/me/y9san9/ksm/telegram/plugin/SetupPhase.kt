package me.y9san9.ksm.telegram.plugin

import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable
import me.y9san9.pipeline.subject.subject

public val SetupPhase: PipelinePhase = buildPipelinePhase {
    name = "SetupPhase"

    runnable {
        subject.require(TelegramFSMBase.Subject.StateList) { "Please setup `routing` in buildTelegramFSM" }
        subject.require(TelegramFSMBase.Subject.Storage) { "Please setup `storage` in buildTelegramFSM" }
    }
}
