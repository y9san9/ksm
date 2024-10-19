package me.y9san9.ksm.state.runner

import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require

public interface StateRunner {
    public val context: PipelineContext

    public companion object {
        public fun of(context: PipelineContext): StateRunner {
            return object : StateRunner {
                override val context = context
                override fun toString() = "StateRunner(context=$context)"
            }
        }
    }
}
