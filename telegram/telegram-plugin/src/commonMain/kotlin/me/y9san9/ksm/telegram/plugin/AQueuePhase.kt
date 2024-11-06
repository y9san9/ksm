package me.y9san9.ksm.telegram.plugin

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import me.y9san9.aqueue.flow.mapInAQueue
import me.y9san9.ksm.telegram.plugin.TelegramPlugin.StateGroup
import me.y9san9.ksm.telegram.plugin.TelegramPlugin.StateGroupList
import me.y9san9.ksm.telegram.plugin.TelegramPlugin.Update
import me.y9san9.ksm.telegram.plugin.TelegramPlugin.UpdateFlow
import me.y9san9.ksm.telegram.plugin.handler.HandlerPlugin
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
        val updatePipeline = require(HandlerPlugin.Pipeline)
        val updates = require(UpdateFlow)
        val stateGroups = require(StateGroupList)

        stateGroups.asFlow().mapInAQueue { stateGroup ->
            updates.filter(stateGroup.filter::filter).mapInAQueue(
                queue = stateGroup.aqueue,
                key = { update -> stateGroup.key?.key(update) }
            ) { update ->
                val updateContext = context.build {
                    context[Update] = update
                    context[StateGroup] = stateGroup
                }
                updatePipeline.proceed(updateContext)
            }.collect()
        }.collect()
    }
}
