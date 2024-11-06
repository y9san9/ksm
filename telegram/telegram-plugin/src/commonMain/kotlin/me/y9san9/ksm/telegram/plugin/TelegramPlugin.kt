package me.y9san9.ksm.telegram.plugin

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.coroutines.flow.Flow
import me.y9san9.ksm.telegram.plugin.group.StateGroupContext
import me.y9san9.ksm.telegram.plugin.handler.HandlerPlugin
import me.y9san9.ksm.telegram.routing.StateDescriptor
import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.buildPipeline
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.insertPhaseLast
import me.y9san9.pipeline.plugin.PipelinePlugin
import me.y9san9.pipeline.plugin.install
import me.y9san9.pipeline.setSubject

public data object TelegramPlugin : PipelinePlugin {
    override val name: String = "TelegramFSMBase"

    public val Pipeline: PipelineElement<Pipeline> by PipelineElement

    public val Bot: PipelineElement<TelegramBot> by PipelineElement
    public val UpdateFlow: PipelineElement<Flow<Update>> by PipelineElement
    public val StateGroupList: PipelineElement<List<StateGroupContext>> by PipelineElement

    public val StateGroup: PipelineElement<StateGroupContext> by PipelineElement
    public val StateDescriptor: PipelineElement<StateDescriptor> by PipelineElement
    public val Update: PipelineElement<Update> by PipelineElement

    private val defaultPipeline = buildPipeline {
        setSubject(StateGroupList, emptyList())

        insertPhaseLast(SetupPhase)
        insertPhaseLast(AQueuePhase)

        context.install(HandlerPlugin)
    }

    override fun apply(context: MutablePipelineContext) {
        context[Pipeline] = defaultPipeline
    }
}
