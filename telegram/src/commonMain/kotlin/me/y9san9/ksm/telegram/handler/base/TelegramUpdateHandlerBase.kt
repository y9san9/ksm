package me.y9san9.ksm.telegram.handler.base

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.update.MessageUpdate
import me.y9san9.ksm.telegram.routing.TelegramStorage
import me.y9san9.ksm.telegram.routing.StateDescriptor
import me.y9san9.ksm.telegram.state.continuation.StateContinuation
import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.buildPipeline
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.insertPhaseLast
import me.y9san9.pipeline.plugin.PipelinePlugin
import me.y9san9.pipeline.setSubject

public object TelegramUpdateHandlerBase : PipelinePlugin {
    override val name: String = "TelegramUpdateHandler"

    override fun apply(context: MutablePipelineContext) {
        context[Config.Pipeline] = buildPipeline {
            insertPhaseLast(RestorePhase)
            insertPhaseLast(StartResetPhase)
            insertPhaseLast(RoutePhase)
            insertPhaseLast(RunPhase)
            insertPhaseLast(GotoLooper)
            insertPhaseLast(SavePhase)
        }

        context.setSubject(Subject.GotoPipeline, buildPipeline {
            insertPhaseLast(GotoRoutePhase)
            insertPhaseLast(GotoRunPhase)
        })
    }

    public object Config {
        public object Pipeline : PipelineElement<me.y9san9.pipeline.Pipeline>
    }

    public object Subject {
        public object Update : PipelineElement<MessageUpdate>
        public object Bot : PipelineElement<TelegramBot>

        public object StateList : PipelineElement<me.y9san9.ksm.telegram.routing.StateList>
        public object Storage : PipelineElement<TelegramStorage>

        public object Descriptor : PipelineElement<StateDescriptor>
        public object State : PipelineElement<me.y9san9.ksm.telegram.state.State>

        public object GotoCommand : PipelineElement<me.y9san9.ksm.telegram.handler.GotoCommand>
        public object GotoPipeline : PipelineElement<Pipeline>

        public object Continuation : PipelineElement<StateContinuation>
    }
}
