package me.y9san9.ksm.telegram.callbackQuery

import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.InlineMessageId
import dev.inmo.tgbotapi.types.MessageId
import me.y9san9.ksm.telegram.handler.RunPhase
import me.y9san9.ksm.telegram.handler.UpdateHandler
import me.y9san9.pipeline.*
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.set
import me.y9san9.pipeline.plugin.PipelinePlugin

public data object CallbackQueryPlugin : PipelinePlugin {
    override val name: String = "CallbackQueryHandlerPlugin"

    public val Pipeline: PipelineElement<Pipeline> by PipelineElement
    public val Update: PipelineElement<dev.inmo.tgbotapi.types.update.CallbackQueryUpdate> by PipelineElement
    public val ChatId: PipelineElement<ChatIdentifier> by PipelineElement
    public val MessageId: PipelineElement<MessageId> by PipelineElement
    public val InlineMessageId: PipelineElement<InlineMessageId> by PipelineElement

    private val defaultPipeline = buildPipeline()

    override fun apply(context: MutablePipelineContext) {
        context.set(UpdateHandler.Pipeline) {
            subject[Pipeline] = defaultPipeline

            insertPhaseBefore(
                RunPhase,
                CallbackQueryPhase
            )
        }
    }
}
