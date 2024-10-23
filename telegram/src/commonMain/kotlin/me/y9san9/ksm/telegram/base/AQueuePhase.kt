package me.y9san9.ksm.telegram.base

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import me.y9san9.aqueue.flow.mapInAQueue
import me.y9san9.ksm.telegram.base.TelegramFSMBase.Subject
import me.y9san9.ksm.telegram.group.*
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
        val handler = require(Subject.Handler)
        val updates = require(Subject.UpdateFlow)
        val stateGroups = require(Subject.StateGroups)

        stateGroups.asFlow().mapInAQueue { stateGroup ->
            updates.filter(stateGroup.filter::filter).mapInAQueue(
                queue = stateGroup.aqueue,
                key = { update -> stateGroup.key?.key(update) }
            ) { update ->
                val subject = toPipelineContext().build {
                    this[TelegramUpdateHandlerBase.Subject.Update] = update
                    this[TelegramUpdateHandlerBase.Subject.StateList] = stateGroup.stateList
                    this[TelegramUpdateHandlerBase.Subject.Storage] = stateGroup.storage
                    this[TelegramUpdateHandlerBase.Subject.Bot] = this[Subject.Bot]
                }
                handler.proceed(subject)
            }.collect()
        }.collect()
    }
}
