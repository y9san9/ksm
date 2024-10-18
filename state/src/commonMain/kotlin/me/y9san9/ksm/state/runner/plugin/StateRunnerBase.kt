package me.y9san9.ksm.state.runner.plugin

import me.y9san9.ksm.state.State
import me.y9san9.ksm.state.StateAction
import me.y9san9.ksm.state.runner.StateRunnerBuilder
import me.y9san9.ksm.state.runner.pipeline
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.insertPhaseLast
import me.y9san9.pipeline.plugin.PipelinePlugin
import me.y9san9.pipeline.plugin.install

public fun StateRunnerBuilder.installStateRunner() {
    context.install(StateRunnerBase)
    StateRunnerBase.apply(builder = this)
}

public object StateRunnerBase : PipelinePlugin {
    override val name: String = "StateRunnerBase"

    public fun apply(builder: StateRunnerBuilder) {
        with(builder) {
            pipeline {
                insertPhaseLast(StateContinuationPhase)
                insertPhaseLast(StateActionPhase)
            }
        }
    }

    public object Subject {
        public object StateAction : PipelineElement<me.y9san9.ksm.state.StateAction>
    }
}
