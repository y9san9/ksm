package me.y9san9.ksm.fsm.plugin

import me.y9san9.ksm.fsm.*
import me.y9san9.ksm.fsm.looper.Looper
import me.y9san9.ksm.fsm.navigation.buildStateNavigator
import me.y9san9.ksm.router.buildStateRouter
import me.y9san9.ksm.state.runner.buildStateRunner
import me.y9san9.pipeline.buildPipeline
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.insertPhaseLast
import me.y9san9.pipeline.plugin.PipelinePlugin

public object FSMBasePlugin : PipelinePlugin {
    override val name: String = "FSMBase"

    public fun apply(builder: FSMBuilder) {
        with(builder) {
            context[FSMPipeline] = buildPipeline {
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
