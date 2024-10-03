package me.y9san9.ksm.state

import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.*

@PipelineDsl
public interface StateBuilder {
    public val context: MutablePipelineContext

    public companion object {
        public fun of(context: PipelineContext? = null): StateBuilder {
            return object : StateBuilder {
                override val context = mutablePipelineContextOf(context = context ?: PipelineContext.Empty)
                override fun toString() = "StateBuilder(context=$context)"
            }
        }
    }
}

public fun StateBuilder.build(): State {
    return State.of(context.toPipelineContext())
}
