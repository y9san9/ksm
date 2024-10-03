package me.y9san9.ksm.fsm.navigation.plugin

import me.y9san9.ksm.fsm.navigation.StateNavigatorBuilder
import me.y9san9.ksm.fsm.navigation.StateNavigatorPipeline
import me.y9san9.ksm.fsm.plugin.FSMRoutePhase
import me.y9san9.ksm.fsm.plugin.FSMRunPhase
import me.y9san9.pipeline.buildPipeline
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.insertPhaseLast
import me.y9san9.pipeline.plugin.PipelinePlugin
import me.y9san9.pipeline.plugin.register

public fun StateNavigatorBuilder.installStateNavigatorBase() {
    context.register(StateNavigatorBasePlugin)
    StateNavigatorBasePlugin.apply(builder = this)
}

public object StateNavigatorBasePlugin : PipelinePlugin {
    override val name: String = "StateNavigatorBase"

    public fun apply(builder: StateNavigatorBuilder) {
        with(builder) {
            context[StateNavigatorPipeline] = buildPipeline {
                insertPhaseLast(FSMRoutePhase)
                insertPhaseLast(FSMRunPhase)
            }
        }
    }
}
