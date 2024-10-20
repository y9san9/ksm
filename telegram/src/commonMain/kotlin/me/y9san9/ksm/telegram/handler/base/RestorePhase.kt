package me.y9san9.ksm.telegram.handler.base

import me.y9san9.ksm.telegram.handler.base.TelegramUpdateHandlerBase.Subject
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
        val storage = context.require(Subject.Storage)

        val bot = context.require(Subject.Bot)
        val update = context.require(Subject.Update)

        val data = storage.restore(bot, update) ?: return@runnable
        val descriptor = StateDescriptor.decode(from = data)

        context[Subject.RestoredDescriptor] = descriptor
    }
}
