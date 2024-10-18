package me.y9san9.ksm.telegram.handler.plugin

import me.y9san9.ksm.router.StateDescriptor
import me.y9san9.ksm.router.plugin.StateRouterBase
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable
import me.y9san9.pipeline.subject.setSubject
import me.y9san9.pipeline.subject.subject

public val RestorePhase: PipelinePhase = buildPipelinePhase {
    name = "RestorePhase"

    runnable {
        val storage = subject.require(TelegramHandlerBase.Subject.Storage)

        val bot = subject.require(TelegramHandlerBase.Subject.Bot)
        val update = subject.require(TelegramHandlerBase.Subject.Update)

        val data = storage.restore(bot, update) ?: return@runnable
        val descriptor = StateDescriptor.decode(from = data)

        setSubject(TelegramHandlerBase.Subject.Descriptor, descriptor)
    }
}
