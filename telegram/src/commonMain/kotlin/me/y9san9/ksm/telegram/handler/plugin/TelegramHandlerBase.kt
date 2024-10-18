package me.y9san9.ksm.telegram.handler.plugin

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.update.MessageUpdate
import me.y9san9.ksm.router.StateDescriptor
import me.y9san9.ksm.router.StateRouter
import me.y9san9.ksm.router.buildStateRouter
import me.y9san9.ksm.state.State
import me.y9san9.ksm.state.runner.StateRunner
import me.y9san9.ksm.telegram.TelegramStorage
import me.y9san9.ksm.telegram.handler.TelegramHandler
import me.y9san9.pipeline.buildPipeline
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.insertPhaseLast
import me.y9san9.pipeline.plugin.PipelinePlugin
import me.y9san9.pipeline.plugin.install
import me.y9san9.pipeline.subject.setSubject

public fun TelegramHandler.Builder.installTelegramHandler() {
    context.install(TelegramHandlerBase)
}

public object TelegramHandlerBase : PipelinePlugin {
    override val name: String = "TelegramHandler"

    override fun apply(context: MutablePipelineContext) {
        context[Config.Pipeline] = buildPipeline {
            insertPhaseLast(RestorePhase)
            insertPhaseLast(SavePhase)
        }
        context.setSubject(Subject.Router, buildStateRouter())
    }

    public object Config {
        public object Pipeline : PipelineElement<me.y9san9.pipeline.Pipeline>
    }

    public object Subject {
        public object Update : PipelineElement<MessageUpdate>
        public object Bot : PipelineElement<TelegramBot>

        public object Router : PipelineElement<StateRouter>
        public object Runner : PipelineElement<StateRunner>
        public object StateList : PipelineElement<me.y9san9.ksm.router.StateList>
        public object Storage : PipelineElement<TelegramStorage>

        public object Descriptor : PipelineElement<StateDescriptor>
        public object State : PipelineElement<me.y9san9.ksm.state.State>
    }
}
