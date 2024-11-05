package me.y9san9.ksm.telegram.handler.base

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.update.abstracts.Update
import me.y9san9.ksm.telegram.callbackQuery.plugin.CallbackQueryPlugin
import me.y9san9.ksm.telegram.group.UpdateStorage
import me.y9san9.ksm.telegram.privateMessage.plugin.PrivateMessagePlugin
import me.y9san9.ksm.telegram.routing.FSMRouter
import me.y9san9.ksm.telegram.routing.base.GotoPhase
import me.y9san9.ksm.telegram.routing.base.SavePhase
import me.y9san9.ksm.telegram.routing.buildFSMRouter
import me.y9san9.ksm.telegram.state.routing.UpdateStateList
import me.y9san9.ksm.telegram.state.UpdateState
import me.y9san9.ksm.telegram.state.routing.GotoCommand
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

public data object UpdateHandlerBase : PipelinePlugin {
    override val name: String = "UpdateHandlerBase"

    public val Pipeline: PipelineElement<Pipeline> by PipelineElement

    public val Update: PipelineElement<Update> by PipelineElement
    public val Bot: PipelineElement<TelegramBot> by PipelineElement

    public val StateList: PipelineElement<UpdateStateList> by PipelineElement
    public val Storage: PipelineElement<UpdateStorage> by PipelineElement
    public val Router: PipelineElement<FSMRouter> by PipelineElement

    public val State: PipelineElement<UpdateState> by PipelineElement

    override fun apply(context: MutablePipelineContext) {
        context[Pipeline] = buildPipeline {
            setSubject(Router, buildFSMRouter())

            insertPhaseLast(RestorePhase)
            insertPhaseLast(FindByNamePhase)
            insertPhaseLast(RunPhase)
            insertPhaseLast(RouterPhase)
            insertPhaseLast(SavePhase)
        }

        context.install(PrivateMessagePlugin)
        context.install(CallbackQueryPlugin)
    }
}
