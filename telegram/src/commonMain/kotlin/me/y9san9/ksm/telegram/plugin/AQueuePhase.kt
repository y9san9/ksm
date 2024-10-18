package me.y9san9.ksm.telegram.plugin

import kotlinx.coroutines.flow.collect
import me.y9san9.aqueue.flow.mapInAQueue
import me.y9san9.ksm.telegram.handler.plugin.TelegramHandlerBase
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.buildPipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable
import me.y9san9.pipeline.subject.subject

public val AQueuePhase: PipelinePhase = buildPipelinePhase {
    name = "AQueuePhase"

    runnable {
        val aqueue = subject.require(TelegramFSMBase.Subject.AQueue)
        val handler = subject.require(TelegramFSMBase.Subject.Handler)
        val updates = subject.require(TelegramFSMBase.Subject.UpdateFlow)

        updates.mapInAQueue(aqueue) { update ->
            val subject = buildPipelineContext {
                this[TelegramHandlerBase.Subject.Bot] = subject[TelegramFSMBase.Subject.Bot]
                this[TelegramHandlerBase.Subject.Update] = update
            }
            handler.proceed(subject)
        }.collect()
    }
}
