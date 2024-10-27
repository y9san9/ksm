package me.y9san9.ksm.telegram.privateMessage.state

import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.types.update.MessageUpdate
import me.y9san9.ksm.telegram.state.update
import me.y9san9.pipeline.annotation.PipelineDsl

@PipelineDsl
public val PrivateMessageHandler.Scope.messageUpdate: MessageUpdate
    get() = update as MessageUpdate

@PipelineDsl
public val PrivateMessageHandler.Scope.message: PrivateContentMessage<*>
    get() = messageUpdate.data as PrivateContentMessage<*>

@PipelineDsl
public val PrivateMessageHandler.Scope.user: User
    get() = message.from
