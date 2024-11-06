package me.y9san9.ksm.telegram.plugin.routing

import me.y9san9.ksm.telegram.plugin.TelegramPlugin.StateDescriptor
import me.y9san9.ksm.telegram.plugin.TelegramPlugin.StateGroup
import me.y9san9.ksm.telegram.plugin.group.StateGroupPlugin.InitialState
import me.y9san9.ksm.telegram.plugin.group.StateGroupPlugin.StateList
import me.y9san9.ksm.telegram.plugin.routing.RoutingPlugin.SelectedState
import me.y9san9.ksm.telegram.plugin.state.StatePlugin.Name
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable

public val FindByNamePhase: PipelinePhase = buildPipelinePhase {
    name = "FindByNamePhase"

    runnable {
        val descriptor = context[StateDescriptor]
        val group = require(StateGroup)

        val stateList = group.require(StateList)
        val initialState = group.require(InitialState)

        if (descriptor == null) {
            context[SelectedState] = initialState
            return@runnable
        }

        for (state in stateList) {
            val name = state.require(Name)
            if (name == descriptor.name) {
                context[SelectedState] = state
                return@runnable
            }
        }

        // TODO: later think how to return errors without throwing them here
        error("Can't find state named '${descriptor.name.string}'")
    }
}
