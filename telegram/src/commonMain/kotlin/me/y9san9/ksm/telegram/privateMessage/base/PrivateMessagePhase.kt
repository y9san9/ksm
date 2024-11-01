package me.y9san9.ksm.telegram.privateMessage.base

import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.types.update.MessageUpdate
import me.y9san9.ksm.telegram.handler.base.TelegramUpdateHandlerBase.Subject
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
        val update = context.require(Subject.Update) as? MessageUpdate ?: return@runnable
        val data = update.data as? PrivateContentMessage<*> ?: return@runnable
        context[Subject.PrivateMessageUpdate] = update
        context[Subject.PrivateMessageData] = data
        context[Subject.PrivateMessageUserId] = data.user.id
        context.require(Subject.PrivateMessage).proceedIn(context)
    }
}
