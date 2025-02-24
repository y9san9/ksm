package me.y9san9.ksm.telegram

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import me.y9san9.aqueue.flow.mapInAQueue
import me.y9san9.ksm.telegram.group.StateGroup
import me.y9san9.ksm.telegram.handler.UpdateHandler
import me.y9san9.pipeline.context.build
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.proceed

public val AQueuePhase: PipelinePhase = buildPipelinePhase {
    name = "AQueuePhase"

    runnable {
        val updates = require(TelegramFSM.UpdateFlow)
        val handlerList = require(TelegramFSM.HandlerList)

        handlerList.asFlow().mapInAQueue { handler ->
            val subject = handler.context.require(UpdateHandler.Subject)

            val aqueue = handler.context.require(UpdateHandler.AQueue)
            val filter = handler.context.require(UpdateHandler.Filter)
            val key = handler.context.require(UpdateHandler.Key)

            updates.filter(filter::filter).mapInAQueue(aqueue, key::key) { update ->
                val updateContext = build {
                    context[TelegramFSM.Update] = update
                    context[UpdateHandler.Subject] = subject
                }
                handler.context.require(UpdateHandler.Pipeline).proceed(updateContext)
            }.collect()
        }.collect()
    }
}
