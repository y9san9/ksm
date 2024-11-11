package me.y9san9.ksm.telegram.privateMessage.group

import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.types.update.MessageUpdate

public fun interface PrivateMessageFilter {
    public suspend fun filter(
        update: MessageUpdate,
        message: PrivateContentMessage<*>
    ): Boolean
}
