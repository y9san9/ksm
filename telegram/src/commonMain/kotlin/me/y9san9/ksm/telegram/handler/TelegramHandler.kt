package me.y9san9.ksm.telegram.handler

import me.y9san9.ksm.telegram.handler.plugin.TelegramHandlerBase
import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.plugin.install
import me.y9san9.pipeline.proceed

public class TelegramHandler(public val context: PipelineContext) {
    public suspend fun proceed(subject: PipelineContext): PipelineContext {
        val pipeline = context.require(TelegramHandlerBase.Config.Pipeline)
        return pipeline.proceed(context, subject)
    }

    public class Builder(context: PipelineContext) {
        public val context: MutablePipelineContext = mutablePipelineContextOf(context)

        public constructor() : this(PipelineContext.Empty) {
            context.install(TelegramHandlerBase)
        }

        public fun build(): TelegramHandler {
            return TelegramHandler(context.toPipelineContext())
        }
    }
}

public inline fun buildTelegramHandler(
    from: TelegramHandler? = null,
    block: TelegramHandler.Builder.() -> Unit = {}
): TelegramHandler {
    val builder = when (from) {
        null -> TelegramHandler.Builder()
        else -> TelegramHandler.Builder(from.context)
    }
    builder.block()
    return builder.build()
}

public inline fun TelegramHandler?.build(block: TelegramHandler.Builder.() -> Unit): TelegramHandler {
    return buildTelegramHandler(from = this, block = block)
}
