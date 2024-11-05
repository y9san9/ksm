package me.y9san9.ksm.telegram.routing.base

import me.y9san9.ksm.telegram.routing.base.FSMRouterBase.GotoPipeline
import me.y9san9.ksm.telegram.state.base.UpdateStateBase.GotoCommand
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable
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
