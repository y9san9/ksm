package me.y9san9.ksm.telegram.handler

import dev.inmo.tgbotapi.types.update.abstracts.Update
import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.buildPipeline
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.mutablePipelineContextOf
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.plugin.PipelinePlugin
import me.y9san9.pipeline.plugin.install

public class TelegramHandler(public val context: PipelineContext) {

    public val pipeline: Pipeline get() = context.require(Pipeline)

    @PipelineDsl
    public class Builder(context: PipelineContext) {
        public val context: MutablePipelineContext = mutablePipelineContextOf(context)

        public constructor() : this(PipelineContext.Empty) {
            context.install(TelegramHandler)
        }

        public fun build(): TelegramHandler {
            return TelegramHandler(context.toPipelineContext())
        }
    }

    public companion object Plugin : PipelinePlugin {
        override val name: String = "TelegramHandler"

        public val Pipeline: PipelineElement<Pipeline> by PipelineElement

        public val Update: PipelineElement<Update> by PipelineElement

        override fun apply(context: MutablePipelineContext) {
            context[Pipeline] = buildPipeline()
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

public fun MutablePipelineContext.set(
    element: PipelineElement<TelegramHandler>,
    block: TelegramHandler.Builder.() -> Unit
  ): TelegramHandler {
    return buildTelegramHandler(context[element], block)
}
