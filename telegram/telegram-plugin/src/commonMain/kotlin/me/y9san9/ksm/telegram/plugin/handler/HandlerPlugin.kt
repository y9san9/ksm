package me.y9san9.ksm.telegram.plugin.handler

import me.y9san9.ksm.telegram.plugin.handler.callbackQuery.CallbackQueryPlugin
import me.y9san9.ksm.telegram.plugin.handler.privateMessage.PrivateMessagePlugin
import me.y9san9.ksm.telegram.routing.base.SavePhase
import me.y9san9.pipeline.*
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.plugin.PipelinePlugin
import me.y9san9.pipeline.plugin.install

public data object HandlerPlugin : PipelinePlugin {
    override val name: String = "UpdateHandlerPlugin"

    public val Pipeline: PipelineElement<Pipeline> by PipelineElement

    override fun apply(context: MutablePipelineContext) {
        context.set(Pipeline) {
            insertPhaseLast(RestorePhase)
            insertPhaseLast(HandlerRoutingPhase)
            insertPhaseLast(RunPhase)
            insertPhaseLast(HandlerTransitionPhase)
        }

        context.install(PrivateMessagePlugin)
        context.install(CallbackQueryPlugin)
    }
}
