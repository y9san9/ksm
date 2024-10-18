package me.y9san9.ksm.router.plugin

import me.y9san9.ksm.router.StateRouterBuilder
import me.y9san9.ksm.state.State
import me.y9san9.pipeline.buildPipeline
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.insertPhaseFirst
import me.y9san9.pipeline.plugin.PipelinePlugin
import me.y9san9.pipeline.plugin.install

public fun StateRouterBuilder.installStateRouter() {
    context.install(StateRouterBase)
    StateRouterBase.apply(builder = this)
}

public object StateRouterBase : PipelinePlugin {
    override val name: String = "StateRouterBase"

    public fun apply(builder: StateRouterBuilder) {
        builder.context[Config.Pipeline] = buildPipeline {
            insertPhaseFirst(RouteByNamePhase)
        }
    }

    public object Config {
        public object Pipeline : PipelineElement<me.y9san9.pipeline.Pipeline>
    }

    public object Subject {
        public object Descriptor : PipelineElement<me.y9san9.ksm.router.StateDescriptor>
        public object StateList : PipelineElement<me.y9san9.ksm.router.StateList>
        public object State : PipelineElement<me.y9san9.ksm.state.State>
    }
}
