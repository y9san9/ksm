package me.y9san9.ksm.telegram.handler.base

import me.y9san9.ksm.telegram.routing.StateDescriptor
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable

public val SavePhase: PipelinePhase = buildPipelinePhase {
    name = "SavePhase"

    runnable {
        val descriptor = context[TelegramUpdateHandlerBase.Subject.Descriptor] ?: return@runnable

        val storage = require(TelegramUpdateHandlerBase.Subject.Storage)
        val bot = require(TelegramUpdateHandlerBase.Subject.Bot)
        val update = require(TelegramUpdateHandlerBase.Subject.Update)

        val data = StateDescriptor.encode(descriptor)

        storage.save(bot, update, data)
    }
}
