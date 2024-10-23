package me.y9san9.ksm.telegram.state.base

import me.y9san9.ksm.telegram.state.StateHandler
import me.y9san9.ksm.telegram.state.StateRoute
import me.y9san9.ksm.telegram.state.StateTransition
import me.y9san9.pipeline.context.PipelineElement

public object StateBase {
    public object Config {
        public object Route : PipelineElement<StateRoute>
        public object Transition : PipelineElement<StateTransition>
        public object Handler : PipelineElement<StateHandler>
    }
}
