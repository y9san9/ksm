package me.y9san9.ksm.telegram.state

import me.y9san9.ksm.telegram.state.base.UpdateStateBase
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.plugin.install

public class UpdateState(public val context: PipelineContext) {

    @PipelineDsl
    public class Builder {
        public val context: MutablePipelineContext = mutablePipelineContextOf()

        init {
            context.install(UpdateStateBase)
        }

        @PipelineDsl
        public var name: StateName?
            get() = context[UpdateStateBase.Name]
            set(value) { context[UpdateStateBase.Name] = value }

        @PipelineDsl
        public var handler: UpdateHandler?
            get() = context[UpdateStateBase.Handler]
            set(value) { context[UpdateStateBase.Handler] = value }

        @PipelineDsl
        public var transition: UpdateTransition?
            get() = context[UpdateStateBase.Transition]
            set(value) { context[UpdateStateBase.Transition] = value }

        public fun build(): UpdateState {
            return UpdateState(context.toPipelineContext())
        }
    }
}
