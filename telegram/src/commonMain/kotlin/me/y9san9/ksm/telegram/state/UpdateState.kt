package me.y9san9.ksm.telegram.state

import me.y9san9.ksm.telegram.state.base.UpdateStateBase
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.mutablePipelineContextOf
import me.y9san9.pipeline.context.require

public class UpdateState(public val context: PipelineContext) {
    init {
        context.require(UpdateStateBase.Config.Route) { "Please set the state name" }
    }

    public open class Builder {
        public val context: MutablePipelineContext = mutablePipelineContextOf()

        public fun build(): UpdateState {
            return UpdateState(context.toPipelineContext())
        }
    }
}
