package me.y9san9.ksm.state

import me.y9san9.pipeline.context.*

public class State(public val context: PipelineContext) {
    public class Builder(context: PipelineContext) {
        public val context: MutablePipelineContext = mutablePipelineContextOf(context)

        public constructor() : this(PipelineContext.Empty)

        public fun build(): State {
            return State(context.toPipelineContext())
        }
    }
}

public inline fun buildState(
    base: State? = null,
    block: State.Builder.() -> Unit
): State {
    val builder = when (base) {
        null -> State.Builder()
        else -> State.Builder(base.context)
    }
    builder.block()
    return builder.build()
}

public fun State?.build(block: State.Builder.() -> Unit): State {
    return buildState(base = this, block = block)
}
