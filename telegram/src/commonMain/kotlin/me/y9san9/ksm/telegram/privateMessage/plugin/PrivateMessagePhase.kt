package me.y9san9.ksm.telegram.privateMessage.plugin

import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.types.update.MessageUpdate
import me.y9san9.ksm.telegram.handler.base.UpdateHandlerBase
import me.y9san9.ksm.telegram.privateMessage.plugin.PrivateMessagePlugin.Message
import me.y9san9.ksm.telegram.privateMessage.plugin.PrivateMessagePlugin.Pipeline
import me.y9san9.ksm.telegram.privateMessage.plugin.PrivateMessagePlugin.Update
import me.y9san9.ksm.telegram.privateMessage.plugin.PrivateMessagePlugin.UserId
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
        val update = require(UpdateHandlerBase.Update) as? MessageUpdate ?: return@runnable
        val data = update.data as? PrivateContentMessage<*> ?: return@runnable

        context[Update] = update
        context[Message] = data
        context[UserId] = data.user.id
        require(Pipeline).proceedIn(context)
    }
}
