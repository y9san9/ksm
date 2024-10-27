package me.y9san9.ksm.telegram.state.base

import me.y9san9.ksm.telegram.state.UpdateHandler
import me.y9san9.ksm.telegram.state.StateName
import me.y9san9.ksm.telegram.state.UpdateTransition
import me.y9san9.pipeline.context.PipelineElement

public object UpdateStateBase {
    public object Config {
        public object Route : PipelineElement<StateName>
        public object Transition : PipelineElement<UpdateTransition>
        public object Handler : PipelineElement<UpdateHandler>
    }
}
