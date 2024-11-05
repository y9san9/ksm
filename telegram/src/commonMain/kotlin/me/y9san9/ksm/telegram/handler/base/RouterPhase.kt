package me.y9san9.ksm.telegram.handler.base

import me.y9san9.ksm.telegram.handler.base.UpdateHandlerBase.Bot
import me.y9san9.ksm.telegram.handler.base.UpdateHandlerBase.Router
import me.y9san9.ksm.telegram.handler.base.UpdateHandlerBase.StateList
import me.y9san9.ksm.telegram.handler.base.UpdateHandlerBase.Storage
import me.y9san9.ksm.telegram.routing.base.FSMRouterBase
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable
import me.y9san9.pipeline.proceedIn

public val RouterPhase: PipelinePhase = buildPipelinePhase {
    name = "RouterPhase"

    runnable {
        val router = require(Router)
        context[FSMRouterBase.Storage] = require(Storage)
        context[FSMRouterBase.Bot] = require(Bot)
        context[FSMRouterBase.StateList] = require(StateList)
        router.pipeline.proceedIn(context)
    }
}
