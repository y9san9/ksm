package me.y9san9.ksm.fsm

import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.require

public interface FSM {
    public val context: PipelineContext

    public companion object : PipelineElement<FSM> {
        public fun of(context: PipelineContext): FSM {
            require(context)
            return object : FSM {
                override val context: PipelineContext = context
                override fun toString() = "FSM(context=$context)"
            }
        }

        public fun require(context: PipelineContext) {
            context.require(FSMPipeline)
            context.require(FSMRouter)
            context.require(FSMRunner)
            context.require(FSMNavigator)
            context.require(FSMStateList) { "Please set up `FSMBuilder.routing` to use FSM" }
        }
    }
}
