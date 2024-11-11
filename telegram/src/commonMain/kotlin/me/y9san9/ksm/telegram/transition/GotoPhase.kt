package me.y9san9.ksm.telegram.transition

import me.y9san9.ksm.telegram.transition.FSMTransition.Plugin.GotoCommand
import me.y9san9.ksm.telegram.transition.FSMTransition.Plugin.GotoPipeline
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.proceedIn

public val GotoPhase: PipelinePhase = buildPipelinePhase {
    name = "GotoPhase"

    runnable {
        while (true) {
            require(GotoPipeline).proceedIn(context)
            val command = require(GotoCommand)
            // Run until no transitions left
            if (!command.transition) break
        }
    }
}
