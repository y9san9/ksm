package me.y9san9.ksm.telegram.state

import me.y9san9.ksm.telegram.state.base.StateBase
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.mutablePipelineContextOf
import me.y9san9.pipeline.context.require

public class State(public val context: PipelineContext) {
    init {
        context.require(StateBase.Config.Route) { "Please set the state name" }
    }

    public class Builder(context: PipelineContext) {
        public val context: MutablePipelineContext = mutablePipelineContextOf(context)

        public constructor() : this(PipelineContext.Empty)

        public fun build(): State {
            return State(context.toPipelineContext())
        }
    }
}
