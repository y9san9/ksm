package me.y9san9.ksm.telegram.routing.base

import me.y9san9.ksm.telegram.handler.base.TelegramUpdateHandlerBase.Subject
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable
import me.y9san9.pipeline.proceedIn

public val GotoLooperPhase: PipelinePhase = buildPipelinePhase {
    name = "GotoLooperPhase"

    runnable {
        while (true) {
            require(Subject.GotoPipeline).proceedIn(context)
            val command = require(Subject.GotoCommand)
            // Run until no transitions left
            if (!command.transition) break
        }
    }
}
