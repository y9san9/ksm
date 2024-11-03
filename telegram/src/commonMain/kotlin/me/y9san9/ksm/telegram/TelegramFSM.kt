package me.y9san9.ksm.telegram

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.longPolling
import dev.inmo.tgbotapi.types.update.MessageUpdate
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import me.y9san9.ksm.telegram.base.TelegramFSMBase
import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.plugin.install
import me.y9san9.pipeline.proceed

public class TelegramFSM(public val context: PipelineContext) {
    public val pipeline: Pipeline = context.require(TelegramFSMBase.Pipeline)

    public suspend inline fun longPolling(
        bot: TelegramBot,
        crossinline block: FlowsUpdatesFilter.() -> Unit = {}
    ) {
        coroutineScope {
            bot.longPolling {
                launch { run(bot, messagesFlow) }
                block()
            }
        }
    }

    public suspend fun run(bot: TelegramBot, messageUpdates: Flow<MessageUpdate>) {
        pipeline.proceed {
            context[TelegramFSMBase.Bot] = bot
            context[TelegramFSMBase.UpdateFlow] = messageUpdates
        }
    }

    @PipelineDsl
    public class Builder(context: PipelineContext) {
        public val context: MutablePipelineContext = mutablePipelineContextOf(context)

        public constructor() : this(PipelineContext.Empty) {
            context.install(TelegramFSMBase)
        }

        public fun build(): TelegramFSM {
            return TelegramFSM(context.toPipelineContext())
        }
    }
}

public inline fun buildTelegramFSM(
    from: TelegramFSM? = null,
    block: TelegramFSM.Builder.() -> Unit = {}
): TelegramFSM {
    val builder = when (from) {
        null -> TelegramFSM.Builder()
        else -> TelegramFSM.Builder(from.context)
    }
    builder.block()
    return builder.build()
}

public inline fun TelegramFSM?.build(block: TelegramFSM.Builder.() -> Unit): TelegramFSM {
    return buildTelegramFSM(from = this, block = block)
}
