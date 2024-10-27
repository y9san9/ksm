package me.y9san9.ksm.telegram.handler.base

import me.y9san9.ksm.telegram.handler.base.TelegramUpdateHandlerBase.Subject
import me.y9san9.ksm.telegram.state.routing.StateDescriptor
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable

public val SavePhase: PipelinePhase = buildPipelinePhase {
    name = "SavePhase"

    runnable {
        val descriptor = require(Subject.GotoCommand).descriptor

        val storage = require(Subject.Storage)
        val bot = require(Subject.Bot)
        val update = require(Subject.Update)

        val data = StateDescriptor.encode(descriptor)

        storage.save(bot, update, data)
    }
}
