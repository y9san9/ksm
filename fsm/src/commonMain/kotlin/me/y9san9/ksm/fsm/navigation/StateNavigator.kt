package me.y9san9.ksm.fsm.navigation

import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require

public interface StateNavigator {
    public val context: PipelineContext

    public companion object {
        public fun of(context: PipelineContext): StateNavigator {
            require(context)

            return object : StateNavigator {
                override val context = context
                override fun toString() = "StateNavigator(context=$context)"
            }
        }

        public fun require(context: PipelineContext) {
            context.require(StateNavigatorPipeline)
        }
    }
}
