package me.y9san9.ksm.telegram.base

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import me.y9san9.aqueue.flow.mapInAQueue
import me.y9san9.ksm.telegram.group.*
import me.y9san9.ksm.telegram.handler.base.UpdateHandlerBase
import me.y9san9.pipeline.context.build
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable
import me.y9san9.pipeline.proceed

public val AQueuePhase: PipelinePhase = buildPipelinePhase {
    name = "AQueuePhase"

    runnable {
        val handler = require(TelegramFSMBase.Handler)
        val updates = require(TelegramFSMBase.UpdateFlow)
        val stateGroups = require(TelegramFSMBase.StateGroups)

        stateGroups.asFlow().mapInAQueue { stateGroup ->
            updates.filter(stateGroup.filter::filter).mapInAQueue(
                queue = stateGroup.aqueue,
                key = { update -> stateGroup.key?.key(update) }
            ) { update ->
                handler.pipeline.proceed(toPipelineContext()) {
                    context[UpdateHandlerBase.Update] = update
                    context[UpdateHandlerBase.StateList] = stateGroup.stateList
                    context[UpdateHandlerBase.Storage] = stateGroup.storage
                    context[UpdateHandlerBase.Bot] = context[TelegramFSMBase.Bot]
                }
            }.collect()
        }.collect()
    }
}
