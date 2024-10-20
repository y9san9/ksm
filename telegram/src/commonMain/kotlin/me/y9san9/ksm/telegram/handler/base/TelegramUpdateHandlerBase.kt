package me.y9san9.ksm.telegram.handler.base

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.update.MessageUpdate
import me.y9san9.ksm.telegram.TelegramStorage
import me.y9san9.ksm.telegram.routing.StateDescriptor
import me.y9san9.ksm.telegram.state.State
import me.y9san9.ksm.telegram.state.continuation.StateContinuation
import me.y9san9.pipeline.buildPipeline
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.insertPhaseLast
import me.y9san9.pipeline.plugin.PipelinePlugin

public object TelegramUpdateHandlerBase : PipelinePlugin {
    override val name: String = "TelegramUpdateHandler"

    override fun apply(context: MutablePipelineContext) {
        context[Config.Pipeline] = buildPipeline {
            insertPhaseLast(RestorePhase)
            insertPhaseLast(StartResetPhase)
            insertPhaseLast(RouteRunPhase)
            insertPhaseLast(RunPhase)
            insertPhaseLast(RouteNavigatePhase)
            insertPhaseLast(NavigatePhase)
            insertPhaseLast(SavePhase)
        }
    }

    public object Config {
        public object Pipeline : PipelineElement<me.y9san9.pipeline.Pipeline>
    }

    public object Subject {
        public object Update : PipelineElement<MessageUpdate>
        public object Bot : PipelineElement<TelegramBot>

        public object StateList : PipelineElement<me.y9san9.ksm.telegram.routing.StateList>
        public object Storage : PipelineElement<TelegramStorage>

        public object RestoredDescriptor : PipelineElement<StateDescriptor>
        public object RestoredState : PipelineElement<State>
        public object GotoCommand : PipelineElement<me.y9san9.ksm.telegram.handler.GotoCommand>
        public object GotoState : PipelineElement<State>
        public object StateData : PipelineElement<me.y9san9.ksm.telegram.state.data.StateData>
        public object Continuation : PipelineElement<StateContinuation>
    }
}
