package me.y9san9.ksm.router

import me.y9san9.ksm.router.descriptor.StateDescriptor
import me.y9san9.ksm.state.State
import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.proceed
import me.y9san9.pipeline.subject.subject

public object StateRouterPipeline : PipelineElement<Pipeline>

public suspend fun StateRouter.route(descriptor: StateDescriptor): State {
    val result = context.require(StateRouterPipeline).proceed(context, descriptor.context)
    return State.of(result.subject)
}
