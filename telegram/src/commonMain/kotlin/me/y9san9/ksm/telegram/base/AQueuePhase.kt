package me.y9san9.ksm.telegram.base

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import me.y9san9.aqueue.flow.mapInAQueue
import me.y9san9.ksm.telegram.base.TelegramFSMBase.Subject
import me.y9san9.ksm.telegram.handler.base.TelegramUpdateHandlerBase
import me.y9san9.ksm.telegram.routing.base.StateListBase
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
        val handler = require(Subject.Handler)
        val updates = require(Subject.UpdateFlow)
        val stateLists = require(Subject.StateLists)

        updates.mapInAQueue { update ->
            stateLists.asFlow().mapInAQueue { stateList ->
                val key = stateList.context.require(StateListBase.Config.Key)
                val queue = stateList.context.require(StateListBase.Config.AQueue)

                queue.execute(key.key(update)) {
                    val subject = toPipelineContext().build {
                        this[TelegramUpdateHandlerBase.Subject.Update] = update
                        this[TelegramUpdateHandlerBase.Subject.StateList] = stateList
                        this[TelegramUpdateHandlerBase.Subject.Bot] = this[Subject.Bot]
                        this[TelegramUpdateHandlerBase.Subject.Storage] = this[Subject.Storage]
                    }
                    handler.proceed(subject)
                }
            }.collect()
        }.collect()
    }
}
