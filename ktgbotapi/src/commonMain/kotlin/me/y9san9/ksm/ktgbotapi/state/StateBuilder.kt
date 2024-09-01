package me.y9san9.ksm.ktgbotapi.state

import me.y9san9.ksm.state.StateName
import me.y9san9.ksm.state.StatePlugin
import me.y9san9.ksm.state.StateRunnable
import me.y9san9.ksm.state.StateTransition
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.with

public class StateBuilder(public var context: PipelineContext)

public inline fun Routing.state(name: String, block: StateBuilder.() -> Unit) {
    val context = PipelineContext.with(StatePlugin.StateName, StateName(name))
    val builder = StateBuilder(context)
    block(builder)
    this.routes += builder.context
}

public fun StateBuilder.handle(block: suspend StateContext.() -> Unit) {
    val runnable = object : StateRunnable {
        override suspend fun run(context: PipelineContext) {
            val state = StateContext(context)
            block(state)
        }
    }
    context = context.with(StatePlugin.StateRunnable, runnable)
}

public fun StateBuilder.transition(block: suspend StateContext.() -> Unit) {
    val transition = object : StateTransition {
        override suspend fun run(context: PipelineContext) {
            val state = StateContext(context)
            block(state)
        }
    }
    context = context.with(StatePlugin.StateTransition, transition)
}
