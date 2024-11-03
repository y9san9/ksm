package me.y9san9.ksm.telegram.base

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.coroutines.flow.Flow
import me.y9san9.ksm.telegram.group.UpdateStateGroup
import me.y9san9.ksm.telegram.handler.UpdateHandler
import me.y9san9.ksm.telegram.handler.buildUpdateHandler
import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.buildPipeline
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.insertPhaseLast
import me.y9san9.pipeline.plugin.PipelinePlugin
import me.y9san9.pipeline.setSubject

public object TelegramFSMBase : PipelinePlugin {
    override val name: String = "TelegramFSMBase"

    public val Pipeline: PipelineElement<Pipeline> by PipelineElement

    public val Handler: PipelineElement<UpdateHandler> by PipelineElement
    public val Bot: PipelineElement<TelegramBot> by PipelineElement
    public val UpdateFlow: PipelineElement<Flow<Update>> by PipelineElement
    public val StateGroups: PipelineElement<List<UpdateStateGroup>> by PipelineElement

    private val defaultPipeline = buildPipeline {
        insertPhaseLast(SetupPhase)
        insertPhaseLast(AQueuePhase)

        setSubject(Handler, buildUpdateHandler())
        setSubject(StateGroups, emptyList())
    }

    override fun apply(context: MutablePipelineContext) {
        context[Pipeline] = defaultPipeline
    }
}
