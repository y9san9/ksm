package me.y9san9.ksm.telegram.plugin

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.update.MessageUpdate
import kotlinx.coroutines.flow.Flow
import me.y9san9.aqueue.AQueue
import me.y9san9.ksm.telegram.TelegramFSM
import me.y9san9.ksm.telegram.TelegramStorage
import me.y9san9.ksm.telegram.handler.TelegramHandler
import me.y9san9.ksm.telegram.handler.buildTelegramHandler
import me.y9san9.ksm.telegram.handler.plugin.TelegramHandlerBase
import me.y9san9.pipeline.buildPipeline
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.insertPhaseLast
import me.y9san9.pipeline.plugin.PipelinePlugin
import me.y9san9.pipeline.plugin.install
import me.y9san9.pipeline.subject.setSubject

public fun TelegramFSM.Builder.installTelegramFSM() {
    context.install(TelegramFSMBase)
}

public object TelegramFSMBase : PipelinePlugin {
    override val name: String = "Telegram"

    override fun apply(context: MutablePipelineContext) {
        context[Config.Pipeline] = buildPipeline {
            insertPhaseLast(SetupPhase)
            insertPhaseLast(AQueuePhase)
        }
        context.setSubject(Subject.Handler, buildTelegramHandler())
        context.setSubject(Subject.AQueue, AQueue())
    }

    public object Config {
        public object Pipeline : PipelineElement<me.y9san9.pipeline.Pipeline>
    }

    public object Subject {
        public object Handler : PipelineElement<TelegramHandler>
        public object AQueue : PipelineElement<me.y9san9.aqueue.AQueue>
        public object Bot : PipelineElement<TelegramBot>
        public object UpdateFlow : PipelineElement<Flow<MessageUpdate>>
    }
}
