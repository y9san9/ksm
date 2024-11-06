package me.y9san9.ksm.telegram.plugin.handler.privateMessage

import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.types.update.MessageUpdate
import me.y9san9.ksm.telegram.plugin.handler.HandlerPlugin
import me.y9san9.pipeline.*
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.plugin.PipelinePlugin
import me.y9san9.pipeline.plugin.dependsOn

public data object PrivateMessagePlugin : PipelinePlugin {
    override val name: String = "PrivateMessagePlugin"

    public val Pipeline: PipelineElement<Pipeline> by PipelineElement
    public val UserId: PipelineElement<UserId> by PipelineElement
    public val Update: PipelineElement<MessageUpdate> by PipelineElement
    public val Message: PipelineElement<PrivateContentMessage<*>> by PipelineElement

    private val defaultPipeline = buildPipeline {
        insertPhaseLast(PrivateMessageStartResetPhase)
    }

    override fun apply(context: MutablePipelineContext) {
        context.dependsOn(HandlerPlugin)

        context.set(HandlerPlugin.Pipeline) {
            setSubject(Pipeline, defaultPipeline)

            insertPhaseFirst(PrivateMessagePhase)
        }
    }
}
