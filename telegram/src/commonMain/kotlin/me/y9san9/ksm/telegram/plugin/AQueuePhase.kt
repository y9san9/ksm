package me.y9san9.ksm.telegram.plugin

import kotlinx.coroutines.flow.collect
import me.y9san9.aqueue.flow.mapInAQueue
import me.y9san9.ksm.telegram.handler.plugin.TelegramHandlerBase
import me.y9san9.ksm.telegram.plugin.TelegramFSMBase.Subject
import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable
import me.y9san9.pipeline.subject.subject

public val AQueuePhase: PipelinePhase = buildPipelinePhase {
    name = "AQueuePhase"

    runnable {
        val aqueue = subject.require(Subject.AQueue)
        val handler = subject.require(Subject.Handler)
        val updates = subject.require(Subject.UpdateFlow)

        updates.mapInAQueue(aqueue, key = { update -> update.data.chat.id }) { update ->
            val subject = subject.build {
                this[TelegramHandlerBase.Subject.Bot] = this[Subject.Bot]
                this[TelegramHandlerBase.Subject.StateList] = this[Subject.StateList]
                this[TelegramHandlerBase.Subject.Storage] = this[Subject.Storage]
                this[TelegramHandlerBase.Subject.Update] = update
            }
            handler.proceed(subject)
        }.collect()
    }
}
