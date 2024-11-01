package me.y9san9.ksm.telegram.state

import me.y9san9.ksm.telegram.state.base.UpdateStateBase
import me.y9san9.ksm.telegram.state.base.UpdateStateBase.Config
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.*

public class UpdateState(public val context: PipelineContext) {
    init {
        context.require(Config.Name) { "Please set the state name" }
    }

    @PipelineDsl
    public class Builder {
        public val context: MutablePipelineContext = mutablePipelineContextOf()

        @PipelineDsl
        public var name: StateName?
            get() = context[Config.Name]
            set(value) { context[Config.Name] = value }

        @PipelineDsl
        public var handler: UpdateHandler?
            get() = context[Config.Handler]
            set(value) { context[Config.Handler] = value }

        @PipelineDsl
        public var transition: UpdateTransition?
            get() = context[Config.Transition]
            set(value) { context[Config.Transition] = value }

        public fun build(): UpdateState {
            return UpdateState(context.toPipelineContext())
        }
    }
}
