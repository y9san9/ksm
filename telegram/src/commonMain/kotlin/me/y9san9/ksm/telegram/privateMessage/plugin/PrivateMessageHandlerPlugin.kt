package me.y9san9.ksm.telegram.privateMessage.plugin

import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.types.update.MessageUpdate
import me.y9san9.ksm.telegram.handler.base.RunPhase
import me.y9san9.ksm.telegram.handler.base.UpdateHandlerBase
import me.y9san9.pipeline.*
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.plugin.PipelinePlugin
import me.y9san9.pipeline.plugin.dependsOn

public object PrivateMessageHandlerPlugin : PipelinePlugin {
    override val name: String = "PrivateMessageBase"

    public val Pipeline: PipelineElement<Pipeline> by PipelineElement
    public val UserId: PipelineElement<UserId> by PipelineElement
    public val Update: PipelineElement<MessageUpdate> by PipelineElement
    public val Message: PipelineElement<PrivateContentMessage<*>> by PipelineElement

    private val defaultPipeline = buildPipeline {
        insertPhaseLast(PrivateMessageStartResetPhase)
    }

    override fun apply(context: MutablePipelineContext) {
        context.dependsOn(UpdateHandlerBase)

        context[UpdateHandlerBase.Pipeline] = context[UpdateHandlerBase.Pipeline].build {
            setSubject(Pipeline, defaultPipeline)

            insertPhaseBefore(RunPhase, PrivateMessagePhase)
        }
    }
}
