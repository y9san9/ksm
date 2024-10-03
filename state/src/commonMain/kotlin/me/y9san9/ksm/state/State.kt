package me.y9san9.ksm.state

import me.y9san9.pipeline.context.*

public interface State {
    public val context: PipelineContext

    public companion object {
        public fun of(context: PipelineContext): State {
            require(context)
            return object : State {
                override val context = context
                override fun toString() = "State(context=$context)"
            }
        }

        public fun require(context: PipelineContext) {
            context.require(StateName) { "StateName was not provided" }
            context.require(StateAction) { "StateAction was not provided" }
        }
    }
}
