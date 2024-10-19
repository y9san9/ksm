package me.y9san9.ksm.state.runner

import me.y9san9.ksm.state.runner.plugin.StateRunnerBase
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.mutablePipelineContextOf
import me.y9san9.pipeline.context.toPipelineContext
import me.y9san9.pipeline.plugin.install

@PipelineDsl
public interface StateRunnerBuilder {
    public val context: MutablePipelineContext

    public companion object {
        public fun of(): StateRunnerBuilder {
            return of(PipelineContext.Empty).apply {
                context.install(StateRunnerBase)
            }
        }

        public fun of(context: PipelineContext? = null): StateRunnerBuilder {
            context ?: return of()
            return object : StateRunnerBuilder {
                override val context = mutablePipelineContextOf(context)
                override fun toString() = "StateRunnerBuilder(context=$context)"
            }
        }
    }
}

public fun StateRunnerBuilder.build(): StateRunner {
    return StateRunner.of(context.toPipelineContext())
}
