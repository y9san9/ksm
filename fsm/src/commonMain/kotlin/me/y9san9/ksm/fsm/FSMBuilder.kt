package me.y9san9.ksm.fsm

import me.y9san9.ksm.fsm.plugin.installFSMBase
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.mutablePipelineContextOf
import me.y9san9.pipeline.context.toPipelineContext

@PipelineDsl
public interface FSMBuilder {
    public val context: MutablePipelineContext

    public companion object {
        public fun of(): FSMBuilder {
            return of(PipelineContext.Empty).apply {
                installFSMBase()
            }
        }

        public fun of(context: PipelineContext? = null): FSMBuilder {
            context ?: return of()
            return object : FSMBuilder {
                override val context = mutablePipelineContextOf(context)
                override fun toString() = "FSMBuilder(context=${this.context})"
            }
        }
    }
}

public fun FSMBuilder.build(): FSM {
    return FSM.of(context.toPipelineContext())
}
