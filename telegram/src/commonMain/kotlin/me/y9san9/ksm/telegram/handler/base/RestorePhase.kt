package me.y9san9.ksm.telegram.handler.base

import me.y9san9.ksm.telegram.handler.base.UpdateHandlerBase.Bot
import me.y9san9.ksm.telegram.handler.base.UpdateHandlerBase.Storage
import me.y9san9.ksm.telegram.state.base.UpdateStateBase.Descriptor
import me.y9san9.ksm.telegram.state.routing.StateDescriptor
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable

public val RestorePhase: PipelinePhase = buildPipelinePhase {
    name = "RestorePhase"

    runnable {
        val storage = context.require(Storage)

        val bot = context.require(Bot)

        val data = storage.restore(bot, toPipelineContext()) ?: return@runnable
        val descriptor = StateDescriptor.decode(from = data)

        context[Descriptor] = descriptor
    }
}
