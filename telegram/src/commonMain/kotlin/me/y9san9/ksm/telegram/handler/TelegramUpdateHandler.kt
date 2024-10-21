package me.y9san9.ksm.telegram.handler

import me.y9san9.ksm.telegram.handler.base.TelegramUpdateHandlerBase
import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.plugin.install
import me.y9san9.pipeline.proceed
import me.y9san9.pipeline.subject

public class TelegramUpdateHandler(public val context: PipelineContext) {
    public suspend fun proceed(subject: PipelineContext): PipelineContext {
        val pipeline = context.require(TelegramUpdateHandlerBase.Config.Pipeline)
        return pipeline.proceed(subject = context.subject + subject)
    }

    public class Builder(context: PipelineContext) {
        public val context: MutablePipelineContext = mutablePipelineContextOf(context)

        public constructor() : this(PipelineContext.Empty) {
            context.install(TelegramUpdateHandlerBase)
        }

        public fun build(): TelegramUpdateHandler {
            return TelegramUpdateHandler(context.toPipelineContext())
        }
    }
}

public inline fun buildTelegramHandler(
    from: TelegramUpdateHandler? = null,
    block: TelegramUpdateHandler.Builder.() -> Unit = {}
): TelegramUpdateHandler {
    val builder = when (from) {
        null -> TelegramUpdateHandler.Builder()
        else -> TelegramUpdateHandler.Builder(from.context)
    }
    builder.block()
    return builder.build()
}

public inline fun TelegramUpdateHandler?.build(block: TelegramUpdateHandler.Builder.() -> Unit): TelegramUpdateHandler {
    return buildTelegramHandler(from = this, block = block)
}
