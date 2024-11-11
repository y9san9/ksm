package me.y9san9.ksm.telegram.flow

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.coroutines.flow.Flow
import me.y9san9.aqueue.AQueue
import me.y9san9.ksm.telegram.handler.TelegramHandler
import me.y9san9.ksm.telegram.handler.buildTelegramHandler
import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.mutablePipelineContextOf
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.insertPhaseLast
import me.y9san9.pipeline.plugin.PipelinePlugin
import me.y9san9.pipeline.plugin.install
import me.y9san9.pipeline.proceed
import me.y9san9.pipeline.set
import me.y9san9.pipeline.subject

public class TelegramFlow(public val context: PipelineContext) {

    public suspend inline fun collect(
        bot: TelegramBot,
        updates: Flow<Update>,
        key: TelegramFlowKey,
        subject: PipelineContext = PipelineContext.Empty,
        block: MutablePipelineContext.() -> Unit = {}
    ) {
        context.require(Pipeline).proceed(subject) {
            context[Bot] = bot
            context[Updates] = updates
            context[Key] = key
            block()
        }
    }

    public class Builder(context: PipelineContext) {
        public val context: MutablePipelineContext = mutablePipelineContextOf(context)

        public constructor() : this(PipelineContext.Empty) {
            context.install(TelegramFlow)
        }

        public fun build(): TelegramFlow {
            return TelegramFlow(context.toPipelineContext())
        }
    }

    public companion object Plugin : PipelinePlugin {
        override val name: String = "TelegramFlow"

        public val Pipeline: PipelineElement<Pipeline> by PipelineElement

        public val Bot: PipelineElement<TelegramBot> by PipelineElement
        public val Updates: PipelineElement<Flow<Update>> by PipelineElement

        public val Handler: PipelineElement<TelegramHandler> by PipelineElement

        public val AQueue: PipelineElement<AQueue> by PipelineElement
        public val Key: PipelineElement<TelegramFlowKey> by PipelineElement

        override fun apply(context: MutablePipelineContext) {
            context.set(Pipeline) {
                subject[Handler] = buildTelegramHandler()
                subject[AQueue] = AQueue()

                insertPhaseLast(AQueuePhase)
            }
        }
    }
}

public inline fun buildTelegramCore(
    from: TelegramFlow? = null,
    block: TelegramFlow.Builder.() -> Unit = {}
): TelegramFlow {
    val builder = when (from) {
        null -> TelegramFlow.Builder()
        else -> TelegramFlow.Builder(from.context)
    }
    builder.block()
    return builder.build()
}

public fun MutablePipelineContext.set(
    element: PipelineElement<TelegramFlow>,
    block: TelegramFlow.Builder.() -> Unit
  ): TelegramFlow {
    return buildTelegramCore(context[element], block)
}

