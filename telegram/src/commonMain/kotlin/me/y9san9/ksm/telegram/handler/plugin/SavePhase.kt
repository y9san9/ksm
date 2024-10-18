package me.y9san9.ksm.telegram.handler.plugin

import me.y9san9.ksm.router.StateDescriptor
import me.y9san9.ksm.router.plugin.StateRouterBase
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable
import me.y9san9.pipeline.subject.subject

public val SavePhase: PipelinePhase = buildPipelinePhase {
    name = "SavePhase"

    runnable {
        val storage = subject.require(TelegramHandlerBase.Subject.Storage)
        val bot = subject.require(TelegramHandlerBase.Subject.Bot)
        val update = subject.require(TelegramHandlerBase.Subject.Update)
        val descriptor = subject.require(TelegramHandlerBase.Subject.Descriptor)

        val data = StateDescriptor.encode(descriptor)

        storage.save(bot, update, data)
    }
}
