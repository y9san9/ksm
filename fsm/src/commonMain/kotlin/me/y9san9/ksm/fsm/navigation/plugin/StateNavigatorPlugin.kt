package me.y9san9.ksm.fsm.navigation.plugin

import me.y9san9.ksm.fsm.navigation.StateNavigatorBuilder
import me.y9san9.ksm.fsm.plugin.FSMRoutePhase
import me.y9san9.ksm.fsm.plugin.FSMRunPhase
import me.y9san9.pipeline.buildPipeline
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.insertPhaseLast
import me.y9san9.pipeline.plugin.PipelinePlugin
import me.y9san9.pipeline.plugin.install

public fun StateNavigatorBuilder.installStateNavigator() {
    context.install(StateNavigatorPlugin)
    StateNavigatorPlugin.apply(builder = this)
}

public object StateNavigatorPlugin : PipelinePlugin {
    override val name: String = "StateNavigatorBase"

    public fun apply(builder: StateNavigatorBuilder) {
        builder.context[Config.Pipeline] = buildPipeline {
            insertPhaseLast(FSMRoutePhase)
            insertPhaseLast(FSMRunPhase)
        }
    }

    public object Config {
        public object Pipeline : PipelineElement<me.y9san9.pipeline.Pipeline>
    }

    public object Subject {
        public object StateDescriptor : PipelineElement<me.y9san9.ksm.router.StateDescriptor>
    }
}
