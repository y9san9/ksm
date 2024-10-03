package me.y9san9.ksm.state

import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.with
import me.y9san9.pipeline._PipelineRunnable

public object StateDataSetupPhase : _PipelineRunnable {
    override val name: String = "StateDataSetup"

    override suspend fun proceed(context: PipelineContext): PipelineContext {
        val descriptor = context[StatePlugin.StateDescriptor]
            ?: return context.with(StatePlugin.StateData, StateData.Map.Empty)

        val data = descriptor.parameters.map.getValue(StateRestore.STATE_DATA) as StateData.Map

        return context.with(StatePlugin.StateData, data)
    }
}
