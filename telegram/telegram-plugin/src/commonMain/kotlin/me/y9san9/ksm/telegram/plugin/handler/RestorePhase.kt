package me.y9san9.ksm.telegram.plugin.handler

import me.y9san9.ksm.telegram.plugin.TelegramPlugin
import me.y9san9.ksm.telegram.plugin.TelegramPlugin.Bot
import me.y9san9.ksm.telegram.plugin.TelegramPlugin.StateGroup
import me.y9san9.ksm.telegram.plugin.group.StateGroupPlugin.Storage
import me.y9san9.ksm.telegram.routing.StateDescriptor
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable

public val RestorePhase: PipelinePhase = buildPipelinePhase {
    name = "RestorePhase"

    runnable {
        val group = context.require(StateGroup)
        val storage = group.require(Storage)

        val bot = context.require(Bot)

        val data = storage.restore(bot, toPipelineContext()) ?: return@runnable
        val descriptor = StateDescriptor.decode(from = data)

        context[TelegramPlugin.StateDescriptor] = descriptor
    }
}
