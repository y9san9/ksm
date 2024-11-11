package me.y9san9.ksm.telegram

import me.y9san9.ksm.telegram.handler.buildUpdateHandler
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase

public val Setup: PipelinePhase = buildPipelinePhase {
    name = "Setup"

    runnable {
        context[TelegramFSM.DefaultHandler] = buildUpdateHandler()
    }
}
