package me.y9san9.ksm.fsm.plugin

import me.y9san9.ksm.fsm.*
import me.y9san9.ksm.fsm.looper.Looper
import me.y9san9.ksm.fsm.navigation.buildStateNavigator
import me.y9san9.ksm.router.buildStateRouter
import me.y9san9.ksm.state.runner.buildStateRunner
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.docs.describe
import me.y9san9.pipeline.docs.description
import me.y9san9.pipeline.docs.name
import me.y9san9.pipeline.docs.plugin.installPipelineDocs
import me.y9san9.pipeline.insertPhaseLast
import me.y9san9.pipeline.plugin.PipelinePlugin

public object FSMBasePlugin : PipelinePlugin {
    override val name: String = "FSMBase"

    public fun apply(builder: FSMBuilder) {
        with(builder) {
            pipeline {
                installPipelineDocs()

                describe {
                    name = "FSMPipeline"
                    description = "Run this pipeline to start FSM"
                }

                insertPhaseLast(FSMRoutePhase)
                insertPhaseLast(FSMRunPhase)
                insertPhaseLast(FSMLooperPhase)
            }
            context[FSMRouter] = buildStateRouter()
            context[FSMRunner] = buildStateRunner()
            context[FSMNavigator] = buildStateNavigator()
            context[Looper] = Looper()
        }
    }
}
