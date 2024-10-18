package me.y9san9.ksm.fsm.navigation

import me.y9san9.ksm.fsm.FSM
import me.y9san9.ksm.router.StateDescriptor
import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.proceed

public suspend fun StateNavigator.navigate(descriptor: StateDescriptor) {
    val fsm = descriptor.context.require(FSM)
    context.require(StateNavigatorPipeline).proceed(fsm.context, descriptor.context)
}
