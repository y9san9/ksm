package me.y9san9.ksm.telegram.handler

import me.y9san9.ksm.telegram.TelegramFSM.Plugin.Transition
import me.y9san9.ksm.telegram.transition.FSMTransition.Plugin.GotoCommand
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.proceedIn

public val HandlerTransitionPhase: PipelinePhase = buildPipelinePhase {
    name = "HandlerTransitionPhase"

    runnable {
        if (GotoCommand in context) {
            require(Transition).pipeline.proceedIn(context)
        }
    }
}
