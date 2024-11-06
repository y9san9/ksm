package me.y9san9.ksm.telegram.plugin.handler.privateMessage

import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.types.update.MessageUpdate
import me.y9san9.ksm.telegram.plugin.handler.HandlerPlugin
import me.y9san9.ksm.telegram.plugin.handler.privateMessage.PrivateMessagePlugin.Message
import me.y9san9.ksm.telegram.plugin.handler.privateMessage.PrivateMessagePlugin.Pipeline
import me.y9san9.ksm.telegram.plugin.handler.privateMessage.PrivateMessagePlugin.Update
import me.y9san9.ksm.telegram.plugin.handler.privateMessage.PrivateMessagePlugin.UserId
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
        val update = require(HandlerPlugin.Update) as? MessageUpdate ?: return@runnable
        val data = update.data as? PrivateContentMessage<*> ?: return@runnable

        context[Update] = update
        context[Message] = data
        context[UserId] = data.user.id
        require(Pipeline).proceedIn(context)
    }
}
