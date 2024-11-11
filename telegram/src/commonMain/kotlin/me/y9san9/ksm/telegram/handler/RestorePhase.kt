package me.y9san9.ksm.telegram.handler

import me.y9san9.ksm.telegram.TelegramFSM
import me.y9san9.ksm.telegram.TelegramFSM.Plugin.Bot
import me.y9san9.ksm.telegram.TelegramFSM.Plugin.StateGroup
import me.y9san9.ksm.telegram.routing.StateDescriptor
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase

public val RestorePhase: PipelinePhase = buildPipelinePhase {
    name = "RestorePhase"

    runnable {
        val group = context.require(StateGroup)
        val storage = group.storage

        val bot = context.require(Bot)

        val data = storage.restore(bot, toPipelineContext()) ?: return@runnable
        val descriptor = StateDescriptor.decode(from = data)

        context[TelegramFSM.StateDescriptor] = descriptor
    }
}
