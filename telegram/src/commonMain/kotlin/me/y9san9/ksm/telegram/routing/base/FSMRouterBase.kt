package me.y9san9.ksm.telegram.routing.base

import dev.inmo.tgbotapi.bot.TelegramBot
import me.y9san9.ksm.telegram.group.UpdateStorage
import me.y9san9.ksm.telegram.state.UpdateState
import me.y9san9.ksm.telegram.state.routing.UpdateStateList
import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.buildPipeline
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.insertPhaseLast
import me.y9san9.pipeline.plugin.PipelinePlugin
import me.y9san9.pipeline.setSubject

public object FSMRouterBase : PipelinePlugin {
    override val name: String = "FSMRouterBase"

    public val Pipeline: PipelineElement<Pipeline> by PipelineElement

    public val StateList: PipelineElement<UpdateStateList> by PipelineElement
    public val Storage: PipelineElement<UpdateStorage> by PipelineElement
    public val Bot: PipelineElement<TelegramBot> by PipelineElement

    public val GotoPipeline: PipelineElement<Pipeline> by PipelineElement

    public val State: PipelineElement<UpdateState> by PipelineElement

    private val defaultGotoPipeline = buildPipeline {
        insertPhaseLast(RouterFindByNamePhase)
        insertPhaseLast(GotoRunPhase)
    }

    public val defaultPipeline: Pipeline = buildPipeline {
        setSubject(GotoPipeline, defaultGotoPipeline)

        insertPhaseLast(GotoPhase)
        insertPhaseLast(SavePhase)
    }

    override fun apply(context: MutablePipelineContext) {
        context[Pipeline] = defaultPipeline
    }
}
