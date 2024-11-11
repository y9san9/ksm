package me.y9san9.ksm.telegram.handler

import me.y9san9.ksm.telegram.TelegramFSM.Plugin.Router
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.proceedIn

public val HandlerRoutingPhase: PipelinePhase = buildPipelinePhase {
    name = "HandlerRoutingPhase"

    runnable {
        val router = require(Router)
        router.pipeline.proceedIn(context)
    }
}
