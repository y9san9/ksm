package me.y9san9.ksm.telegram.flow

import kotlinx.coroutines.flow.collect
import me.y9san9.aqueue.flow.mapInAQueue
import me.y9san9.ksm.telegram.handler.TelegramHandler
import me.y9san9.ksm.telegram.parallel.ParallelPlugin
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.proceed

public val AQueuePhase: PipelinePhase = buildPipelinePhase {
    name = "ParallelPhase"

    runnable {
        val flow = require(TelegramFlow.Updates)
        val handler = require(TelegramFlow.Handler)
        val queue = require(ParallelPlugin.AQueue)
        val key = context[ParallelPlugin.Key]

        flow.mapInAQueue(
            queue = queue,
            key = { update -> key?.key(update) },
            transform = { update ->
                handler.pipeline.proceed(toPipelineContext()) {
                    context[TelegramHandler.Update] = update
                }
            }
        ).collect()
    }
}
