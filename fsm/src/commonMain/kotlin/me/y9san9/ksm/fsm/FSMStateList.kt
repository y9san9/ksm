package me.y9san9.ksm.fsm

import me.y9san9.ksm.router.StateList
import me.y9san9.ksm.routing.StateRouting
import me.y9san9.ksm.routing.buildStateList
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.set

public data object FSMStateList : PipelineElement<StateList>

public inline fun FSMBuilder.routing(block: StateRouting.() -> Unit) {
    context[FSMStateList] = buildStateList(context[FSMStateList], block)
}
