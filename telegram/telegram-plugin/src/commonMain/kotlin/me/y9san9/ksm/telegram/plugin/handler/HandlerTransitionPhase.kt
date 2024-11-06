package me.y9san9.ksm.telegram.plugin.handler

import me.y9san9.ksm.telegram.plugin.transition.GotoPlugin
import me.y9san9.ksm.telegram.plugin.transition.TransitionPlugin
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable
import me.y9san9.pipeline.proceedIn

public val HandlerTransitionPhase: PipelinePhase = buildPipelinePhase {
    name = "HandlerTransitionPhase"

    runnable {
        if (GotoPlugin.Command in context) {
            context.require(TransitionPlugin.Pipeline).proceedIn(context)
        }
    }
}
