package me.y9san9.ksm.telegram.handler.base

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.update.abstracts.Update
import me.y9san9.ksm.telegram.callbackQuery.plugin.CallbackQueryHandlerPlugin
import me.y9san9.ksm.telegram.group.UpdateStorage
import me.y9san9.ksm.telegram.privateMessage.plugin.PrivateMessageHandlerPlugin
import me.y9san9.ksm.telegram.privateMessage.plugin.PrivateMessagePhase
import me.y9san9.ksm.telegram.routing.base.GotoPhase
import me.y9san9.ksm.telegram.routing.base.GotoRoutePhase
import me.y9san9.ksm.telegram.routing.base.GotoRunPhase
import me.y9san9.ksm.telegram.routing.base.SavePhase
import me.y9san9.ksm.telegram.state.routing.UpdateStateList
import me.y9san9.ksm.telegram.state.UpdateState
import me.y9san9.ksm.telegram.state.routing.StateDescriptor
import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.buildPipeline
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.insertPhaseLast
import me.y9san9.pipeline.plugin.PipelinePlugin
import me.y9san9.pipeline.plugin.install
import me.y9san9.pipeline.setSubject

public object UpdateHandlerBase : PipelinePlugin {
    override val name: String = "UpdateHandlerBase"

    private val defaultGotoPipeline = buildPipeline {
        insertPhaseLast(GotoRoutePhase)
        insertPhaseLast(GotoRunPhase)
    }

    override fun apply(context: MutablePipelineContext) {
        context[Pipeline] = buildPipeline {
            insertPhaseLast(RestorePhase)
            insertPhaseLast(FindByNamePhase)
            insertPhaseLast(RunPhase)
            insertPhaseLast(GotoPhase)
            insertPhaseLast(SavePhase)

            setSubject(GotoPipeline, defaultGotoPipeline)
        }

        context.install(PrivateMessageHandlerPlugin)
        context.install(CallbackQueryHandlerPlugin)
    }

    public val Pipeline: PipelineElement<Pipeline> by PipelineElement

    public val Update: PipelineElement<Update> by PipelineElement
    public val Bot: PipelineElement<TelegramBot> by PipelineElement

    public val StateList: PipelineElement<UpdateStateList> by PipelineElement
    public val Storage: PipelineElement<UpdateStorage> by PipelineElement

    public val Descriptor: PipelineElement<StateDescriptor> by PipelineElement
    public val State: PipelineElement<UpdateState> by PipelineElement

    public val GotoContinuation: PipelineElement<me.y9san9.ksm.telegram.state.routing.GotoContinuation> by PipelineElement
    public val GotoCommand: PipelineElement<me.y9san9.ksm.telegram.state.routing.GotoCommand> by PipelineElement
    public val GotoPipeline: PipelineElement<Pipeline> by PipelineElement
}
