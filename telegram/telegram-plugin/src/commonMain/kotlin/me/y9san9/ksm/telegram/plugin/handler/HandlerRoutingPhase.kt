package me.y9san9.ksm.telegram.plugin.handler

import me.y9san9.ksm.telegram.plugin.routing.RoutingPlugin
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable
import me.y9san9.pipeline.proceedIn

public val HandlerRoutingPhase: PipelinePhase = buildPipelinePhase {
    name = "HandlerRoutingPhase"

    runnable {
        require(RoutingPlugin.Pipeline).proceedIn(context)
    }
}
