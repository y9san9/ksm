package me.y9san9.ksm.telegram.state.routing

import me.y9san9.ksm.telegram.state.StateName
import me.y9san9.ksm.telegram.state.base.UpdateStateBase.Continuation
import me.y9san9.ksm.telegram.state.base.UpdateStateBase.GotoCommand
import me.y9san9.ksm.telegram.state.data.StateData
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.buildPipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set

@PipelineDsl
public suspend fun StateRouter.goto(
    route: StateName,
    data: StateData = StateData.Null,
    transition: Boolean = true
): Nothing {
    goto(StateDescriptor(route.string, StateData.Map.Empty, data), transition)
}

@PipelineDsl
public suspend fun StateRouter.goto(
    descriptor: StateDescriptor,
    transition: Boolean = true
): Nothing {
    val subject = buildPipelineContext {
        context[GotoCommand] = GotoCommand(descriptor, transition)
    }
    context.require(Continuation).resumeWith(subject)
}
