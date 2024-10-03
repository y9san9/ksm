package me.y9san9.ksm.router.descriptor

import me.y9san9.pipeline.context.PipelineContext

public interface StateDescriptor {
    public val context: PipelineContext

    public companion object {
        public fun of(context: PipelineContext): StateDescriptor {
            return object : StateDescriptor {
                override val context = context
                override fun toString() = "StateDescriptor(context=$context)"
            }
        }
    }
}
