package me.y9san9.ksm.telegram.state.routing

import me.y9san9.ksm.telegram.TelegramFSM.Plugin.StateDescriptor
import me.y9san9.ksm.telegram.state.data.StateData
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require

@PipelineDsl
public interface StateRouter {
    public val context: PipelineContext

    public val data: StateData get() = context.require(StateDescriptor).data
}
