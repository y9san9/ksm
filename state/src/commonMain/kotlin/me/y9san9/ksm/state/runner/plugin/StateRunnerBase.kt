package me.y9san9.ksm.state.runner.plugin

import me.y9san9.pipeline.buildPipeline
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.insertPhaseLast
import me.y9san9.pipeline.plugin.PipelinePlugin

public object StateRunnerBase : PipelinePlugin {
    override val name: String = "StateRunnerBase"

    override fun apply(context: MutablePipelineContext) {
        context[Config.Pipeline] = buildPipeline {
            insertPhaseLast(StateActionPhase)
        }
    }

    public object Config {
        public object Pipeline : PipelineElement<me.y9san9.pipeline.Pipeline>
    }

    public object Subject {
        public object StateAction : PipelineElement<me.y9san9.ksm.state.StateHandler>
        public object StateContinuation: PipelineElement<me.y9san9.ksm.state.continuation.StateContinuation>
    }
}
