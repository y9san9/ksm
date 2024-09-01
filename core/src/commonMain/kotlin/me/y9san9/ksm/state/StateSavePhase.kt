package me.y9san9.ksm.state

import me.y9san9.ksm.restore.RestorePlugin
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.with
import me.y9san9.pipeline.phase.PipelinePhase

public object StateSavePhase : PipelinePhase {
    override val name: String = "StateSave"

    override suspend fun proceed(context: PipelineContext): PipelineContext {
        val descriptor = context.require(StatePlugin.StateDescriptor)
        val data = stateDataOf(
            StateRestore.STATE_NAME to stateDataOf(descriptor.name.string),
            StateRestore.STATE_PARAMETERS to descriptor.parameters
        )
        return context.with(RestorePlugin.Data, data)
    }
}
