package me.y9san9.ksm.telegram.privateMessage

import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.types.update.MessageUpdate
import me.y9san9.ksm.telegram.TelegramFSM
import me.y9san9.ksm.telegram.handler.UpdateFilter
import me.y9san9.ksm.telegram.handler.UpdateHandler
import me.y9san9.ksm.telegram.handler.UpdateKey
import me.y9san9.ksm.telegram.handler.buildUpdateHandler
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.set

public val RegisterPrivateMessage: PipelinePhase = buildPipelinePhase {
    name = "RegisterPrivateMessage"

    runnable {
        val handlerList = require(TelegramFSM.HandlerList).toMutableList()

        val defaultHandler = require(PrivateMessagePlugin.DefaultHandler)
        val groups = require(PrivateMessagePlugin.StateGroupList)

        for (group in groups) {
            val handler = buildUpdateHandler(defaultHandler) {
                context[UpdateHandler.AQueue] = group.aqueue

                context[UpdateHandler.Filter] = UpdateFilter { update ->
                    if (update !is MessageUpdate) return@UpdateFilter false
                    val message = update.data as? PrivateContentMessage<*> ?: return@UpdateFilter false
                    group.filter.filter(update, message)
                }

                context[UpdateHandler.Key] = UpdateKey { update ->
                    group.key.key(update as MessageUpdate, update.data as PrivateContentMessage<*>)
                }

                context[UpdateHandler.Subject] = group.context

                context.set(UpdateHandler.Pipeline) {

                }
            }
            handlerList += handler
        }

        context[TelegramFSM.HandlerList] = handlerList.toList()
    }
}

private suspend fun main() {

}
