package me.y9san9.ksm.state

import me.y9san9.ksm.restore.RestorePlugin
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.with
import me.y9san9.pipeline._PipelineRunnable

public object StateRestorePhase : _PipelineRunnable {
    override val name: String = "StateRestore"

    override suspend fun proceed(context: PipelineContext): PipelineContext {
        val data = context.require(RestorePlugin.Data)
        val name = data.map.getValue(StateRestore.STATE_NAME) as StateData.String
        val parameters = data.map.getValue(StateRestore.STATE_PARAMETERS) as StateData.Map
        val descriptor = StateDescriptor(StateName(name.string), parameters)
        return context.with(StatePlugin.StateDescriptor, descriptor).with(StatePlugin.StateData, data)
    }
}
