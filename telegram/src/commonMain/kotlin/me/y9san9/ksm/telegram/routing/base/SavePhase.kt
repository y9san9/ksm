package me.y9san9.ksm.telegram.routing.base

import me.y9san9.ksm.telegram.routing.base.FSMRouterBase.Bot
import me.y9san9.ksm.telegram.routing.base.FSMRouterBase.Storage
import me.y9san9.ksm.telegram.state.base.UpdateStateBase.GotoCommand
import me.y9san9.ksm.telegram.routing.StateDescriptor
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable

public val SavePhase: PipelinePhase = buildPipelinePhase {
    name = "SavePhase"

    runnable {
        val descriptor = require(GotoCommand).descriptor

        val storage = require(Storage)
        val bot = require(Bot)

        val data = StateDescriptor.encode(descriptor)
        storage.save(bot, toPipelineContext(), data)
    }
}
