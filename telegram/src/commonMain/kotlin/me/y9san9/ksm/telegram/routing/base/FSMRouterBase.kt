package me.y9san9.ksm.telegram.routing.base

import dev.inmo.tgbotapi.bot.TelegramBot
import me.y9san9.ksm.telegram.group.UpdateStorage
import me.y9san9.pipeline.buildPipeline
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.insertPhaseLast
import me.y9san9.pipeline.plugin.PipelinePlugin

public object FSMRouterBase : PipelinePlugin {
    override val name: String = "FSMRouterBase"


    public val Pipeline: PipelineElement<me.y9san9.pipeline.Pipeline> by PipelineElement

    public val Storage: PipelineElement<UpdateStorage> by PipelineElement
    public val Bot: PipelineElement<TelegramBot> by PipelineElement

    public val GotoPipeline: PipelineElement<me.y9san9.pipeline.Pipeline> by PipelineElement
    public val GotoCommand: PipelineElement<me.y9san9.ksm.telegram.state.routing.GotoCommand> by PipelineElement

    public val defaultPipeline: me.y9san9.pipeline.Pipeline = buildPipeline {
        insertPhaseLast(GotoPhase)
        insertPhaseLast(SavePhase)
    }

    private val defaultGotoPipeline = buildPipeline {
        insertPhaseLast(GotoRoutePhase)
        insertPhaseLast(GotoRunPhase)
    }

    override fun apply(context: MutablePipelineContext) {
        context[Pipeline] = defaultPipeline
    }
}
