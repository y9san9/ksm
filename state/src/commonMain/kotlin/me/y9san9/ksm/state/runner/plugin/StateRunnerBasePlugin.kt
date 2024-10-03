package me.y9san9.ksm.state.runner.plugin

import me.y9san9.ksm.state.runner.StateRunnerBuilder
import me.y9san9.ksm.state.runner.pipeline
import me.y9san9.pipeline.docs.describe
import me.y9san9.pipeline.docs.description
import me.y9san9.pipeline.docs.name
import me.y9san9.pipeline.docs.plugin.installPipelineDocs
import me.y9san9.pipeline.insertPhaseLast
import me.y9san9.pipeline.plugin.PipelinePlugin
import me.y9san9.pipeline.plugin.register

public fun StateRunnerBuilder.installStateRunnerBase() {
    context.register(StateRunnerBasePlugin)
    StateRunnerBasePlugin.apply(builder = this)
}

public object StateRunnerBasePlugin : PipelinePlugin {
    override val name: String = "StateRunnerBase"

    public fun apply(builder: StateRunnerBuilder) {
        with(builder) {
            pipeline {
                installPipelineDocs()

                describe {
                    name = "StateRunnerPipeline"
                    description = "Run this pipeline to setup, run and cleanup state"
                }

                insertPhaseLast(StateContinuationPhase)
                insertPhaseLast(StateActionPhase)
            }
        }
    }
}
