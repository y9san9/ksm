package me.y9san9.ksm.state

import me.y9san9.pipeline.context.PipelineContext

public interface StateScope {
    public val context: PipelineContext

    public companion object {
        public fun of(context: PipelineContext): StateScope {
            return object : StateScope {
                override val context = context
                override fun toString() = "StateScope(context=$context)"
            }
        }
    }
}
