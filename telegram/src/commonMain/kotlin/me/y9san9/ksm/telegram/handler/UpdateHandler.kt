package me.y9san9.ksm.telegram.handler

import me.y9san9.ksm.telegram.handler.base.UpdateHandlerBase
import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.plugin.install

public class UpdateHandler(public val context: PipelineContext) {
    public val pipeline: Pipeline = context.require(UpdateHandlerBase.Pipeline)

    public class Builder(context: PipelineContext) {
        public val context: MutablePipelineContext = mutablePipelineContextOf(context)

        public constructor() : this(PipelineContext.Empty) {
            context.install(UpdateHandlerBase)
        }

        public fun build(): UpdateHandler {
            return UpdateHandler(context.toPipelineContext())
        }
    }
}

public inline fun buildUpdateHandler(
    from: UpdateHandler? = null,
    block: UpdateHandler.Builder.() -> Unit = {}
): UpdateHandler {
    val builder = when (from) {
        null -> UpdateHandler.Builder()
        else -> UpdateHandler.Builder(from.context)
    }
    builder.block()
    return builder.build()
}

public inline fun UpdateHandler?.build(block: UpdateHandler.Builder.() -> Unit): UpdateHandler {
    return buildUpdateHandler(from = this, block = block)
}
