package me.y9san9.ksm.router

import me.y9san9.pipeline.context.*

public interface StateRouter {
    public val context: PipelineContext

    public companion object {
        public fun of(context: PipelineContext): StateRouter {
            require(context)
            return object : StateRouter {
                override val context = context
                override fun toString(): String = "Router(context=$context)"
            }
        }

        public fun require(context: PipelineContext) {
            context.require(StateRouterPipeline) { "Please provide StateList. You may use `routing` for that" }
        }
    }
}
