package me.y9san9.ksm.state.plugin

import me.y9san9.ksm.state.StateHandler
import me.y9san9.ksm.state.StateTransition
import me.y9san9.pipeline.context.PipelineElement

public object StateBase {
    public object Config {
        public object Handler : PipelineElement<StateHandler>
        public object Transition : PipelineElement<StateTransition>
        public object Name : PipelineElement<String>
    }
}
