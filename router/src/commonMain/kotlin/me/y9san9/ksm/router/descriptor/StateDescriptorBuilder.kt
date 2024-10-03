package me.y9san9.ksm.router.descriptor

import me.y9san9.ksm.state.data.stateDataMapOf
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.*

@PipelineDsl
public interface StateDescriptorBuilder {
    public val context: MutablePipelineContext

    public companion object {
        public fun of(): StateDescriptorBuilder {
            return of(PipelineContext.Empty).apply {
                parameters = stateDataMapOf()
            }
        }

        public fun of(context: PipelineContext? = null): StateDescriptorBuilder {
            context ?: return of()

            return object : StateDescriptorBuilder {
                override val context = mutablePipelineContextOf(context)
                override fun toString(): String = "StateDescriptorBuilder(context=${this.context})"
            }
        }
    }
}

public fun StateDescriptorBuilder.build(): StateDescriptor {
    return StateDescriptor.of(context.toPipelineContext())
}
