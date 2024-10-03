package me.y9san9.ksm.router

import me.y9san9.ksm.state.State
import me.y9san9.pipeline.context.PipelineElement

public class StateList(
    public val initial: State,
    public val list: List<State>
) {
    public companion object : PipelineElement<StateList>
}
