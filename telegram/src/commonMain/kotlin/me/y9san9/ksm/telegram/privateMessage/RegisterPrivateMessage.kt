package me.y9san9.ksm.telegram.privateMessage

import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.types.update.MessageUpdate
import me.y9san9.ksm.telegram.TelegramFSM
import me.y9san9.ksm.telegram.group.StateGroup
import me.y9san9.ksm.telegram.handler.UpdateFilter
import me.y9san9.ksm.telegram.handler.UpdateKey
import me.y9san9.ksm.telegram.handler.buildUpdateHandler
import me.y9san9.ksm.telegram.privateMessage.group.buildPrivateMessageGroup
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase

public val RegisterPrivateMessage: PipelinePhase = buildPipelinePhase {
    name = "RegisterPrivateMessage"

    runnable {
        val handlerList = require(TelegramFSM.HandlerList).toMutableList()

        val defaultHandler = require(PrivateMessagePlugin.DefaultHandler)
        val groups = require(PrivateMessagePlugin.StateGroupList)

        for (group in groups) {
            val handler = buildUpdateHandler(defaultHandler) {
                context[TelegramFSM.StateGroup] = buildPrivateMessageGroup(group) {
                    context[StateGroup.Filter] = UpdateFilter { update ->
                        if (update !is MessageUpdate) return@UpdateFilter false
                        val message = update.data as? PrivateContentMessage<*> ?: return@UpdateFilter false
                        group.filter.filter(update, message)
                    }
                    context[StateGroup.Key] = UpdateKey { update ->
                        group.key.key(update as MessageUpdate, update.data as PrivateContentMessage<*>)
                    }
                }
            }
            handlerList += handler
        }

        context[TelegramFSM.HandlerList] = handlerList.toList()
    }
}
