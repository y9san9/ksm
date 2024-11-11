package me.y9san9.ksm.telegram.transition

import me.y9san9.ksm.telegram.TelegramFSM.Plugin.Router
import me.y9san9.ksm.telegram.TelegramFSM.Plugin.StateDescriptor
import me.y9san9.ksm.telegram.routing.FSMRouter
import me.y9san9.ksm.telegram.routing.GotoCommand
import me.y9san9.ksm.telegram.transition.FSMTransition.Plugin.GotoCommand
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.proceedIn

public val TransitionRouterPhase: PipelinePhase = buildPipelinePhase {
    name = "TransitionRouterPhase"

    runnable {
        val router = require(Router)
        context[StateDescriptor] = context[GotoCommand]?.descriptor
        router.pipeline.proceedIn(context)
    }
}
