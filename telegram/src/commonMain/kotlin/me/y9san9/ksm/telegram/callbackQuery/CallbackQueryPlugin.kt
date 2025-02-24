package me.y9san9.ksm.telegram.callbackQuery

import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.InlineMessageId
import dev.inmo.tgbotapi.types.MessageId
import me.y9san9.ksm.telegram.Register
import me.y9san9.ksm.telegram.Setup
import me.y9san9.ksm.telegram.TelegramFSM
import me.y9san9.ksm.telegram.callbackQuery.group.CallbackQueryGroup
import me.y9san9.ksm.telegram.group.StateGroup
import me.y9san9.ksm.telegram.handler.RunPhase
import me.y9san9.ksm.telegram.handler.UpdateHandler
import me.y9san9.ksm.telegram.privateMessage.PrivateMessagePlugin.StateGroupList
import me.y9san9.ksm.telegram.privateMessage.RegisterPrivateMessage
import me.y9san9.ksm.telegram.privateMessage.SetupPrivateMessage
import me.y9san9.pipeline.*
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.set
import me.y9san9.pipeline.plugin.PipelinePlugin

public data object CallbackQueryPlugin : PipelinePlugin {
    override val name: String = "CallbackQueryHandlerPlugin"

    public val DefaultHandler: PipelineElement<UpdateHandler> by PipelineElement
    public val StateGroupList: PipelineElement<List<CallbackQueryGroup>> by PipelineElement

    public val Update: PipelineElement<dev.inmo.tgbotapi.types.update.CallbackQueryUpdate> by PipelineElement
    public val ChatId: PipelineElement<ChatIdentifier> by PipelineElement
    public val MessageId: PipelineElement<MessageId> by PipelineElement
    public val InlineMessageId: PipelineElement<InlineMessageId> by PipelineElement

    override fun apply(context: MutablePipelineContext) {
        context.set(TelegramFSM.Subject) {
            this[StateGroupList] = emptyList()
        }

        context.set(TelegramFSM.Pipeline) {
            insertPhaseAfter(Setup, SetupCallbackQuery)
            insertPhaseAfter(Register, RegisterCallbackQuery)
        }
    }
}
