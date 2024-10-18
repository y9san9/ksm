package me.y9san9.ksm.state.plugin

import me.y9san9.ksm.state.StateAction
import me.y9san9.pipeline.context.PipelineElement

public object StateBase {
    public object Config {
        public object Action : PipelineElement<StateAction>
        public object Name : PipelineElement<String>
    }
}
