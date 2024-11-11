package me.y9san9.ksm.telegram.privateMessage

import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.types.update.MessageUpdate
import me.y9san9.ksm.telegram.Setup
import me.y9san9.ksm.telegram.Register
import me.y9san9.ksm.telegram.TelegramFSM
import me.y9san9.ksm.telegram.handler.UpdateHandler
import me.y9san9.ksm.telegram.privateMessage.group.PrivateMessageGroup
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.insertPhaseAfter
import me.y9san9.pipeline.plugin.PipelinePlugin
import me.y9san9.pipeline.set
import me.y9san9.pipeline.subject

public data object PrivateMessagePlugin : PipelinePlugin {
    override val name: String = "PrivateMessagePlugin"

    public val DefaultHandler: PipelineElement<UpdateHandler> by PipelineElement

    public val UserId: PipelineElement<UserId> by PipelineElement
    public val Update: PipelineElement<MessageUpdate> by PipelineElement
    public val Message: PipelineElement<PrivateContentMessage<*>> by PipelineElement
    public val StateGroupList: PipelineElement<List<PrivateMessageGroup>> by PipelineElement

    override fun apply(context: MutablePipelineContext) {
        context.set(TelegramFSM.Pipeline) {
            subject[StateGroupList] = context[StateGroupList] ?: emptyList()

            insertPhaseAfter(Setup, SetupPrivateMessage)
            insertPhaseAfter(Register, RegisterPrivateMessage)
        }
    }
}
