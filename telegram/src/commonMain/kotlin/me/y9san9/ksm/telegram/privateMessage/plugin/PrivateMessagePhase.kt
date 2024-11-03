package me.y9san9.ksm.telegram.privateMessage.plugin

import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.types.update.MessageUpdate
import me.y9san9.ksm.telegram.handler.base.UpdateHandlerBase.Update
import me.y9san9.ksm.telegram.privateMessage.plugin.PrivateMessageHandlerPlugin.Message
import me.y9san9.ksm.telegram.privateMessage.plugin.PrivateMessageHandlerPlugin.Pipeline
import me.y9san9.ksm.telegram.privateMessage.plugin.PrivateMessageHandlerPlugin.UserId
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable
import me.y9san9.pipeline.proceedIn

public val PrivateMessagePhase: PipelinePhase = buildPipelinePhase {
    name = "PrivateMessagePhase"

    runnable {
        val update = context.require(Update) as? MessageUpdate ?: return@runnable
        val data = update.data as? PrivateContentMessage<*> ?: return@runnable
        context[PrivateMessageHandlerPlugin.Update] = update
        context[Message] = data
        context[UserId] = data.user.id
        context.require(Pipeline).proceedIn(context)
    }
}
