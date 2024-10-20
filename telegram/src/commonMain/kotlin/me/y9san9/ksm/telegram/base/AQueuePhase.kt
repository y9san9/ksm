package me.y9san9.ksm.telegram.base

import kotlinx.coroutines.flow.collect
import me.y9san9.aqueue.flow.mapInAQueue
import me.y9san9.ksm.telegram.base.TelegramFSMBase.Subject
import me.y9san9.ksm.telegram.handler.base.TelegramUpdateHandlerBase
import me.y9san9.pipeline.context.build
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable

public val AQueuePhase: PipelinePhase = buildPipelinePhase {
    name = "AQueuePhase"

    runnable {
        val aqueue = require(Subject.AQueue)
        val handler = require(Subject.Handler)
        val updates = require(Subject.UpdateFlow)

        updates.mapInAQueue(aqueue, key = { update -> update.data.chat.id }) { update ->
            val subject = toPipelineContext().build {
                this[TelegramUpdateHandlerBase.Subject.Bot] = this[Subject.Bot]
                this[TelegramUpdateHandlerBase.Subject.StateList] = this[Subject.StateList]
                this[TelegramUpdateHandlerBase.Subject.Storage] = this[Subject.Storage]
                this[TelegramUpdateHandlerBase.Subject.Update] = update
            }
            handler.proceed(subject)
        }.collect()
    }
}
