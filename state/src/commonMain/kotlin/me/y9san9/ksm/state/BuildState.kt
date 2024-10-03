package me.y9san9.ksm.state

import me.y9san9.pipeline.context.toPipelineContext

public inline fun buildState(
    base: State? = null,
    block: StateBuilder.() -> Unit
): State {
    val builder = StateBuilder.of(base?.context)
    builder.apply(block)
    return State.of(builder.context.toPipelineContext())
}

public fun State.build(block: StateBuilder.() -> Unit): State {
    return buildState(base = this, block = block)
}
