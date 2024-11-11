package me.y9san9.ksm.telegram.privateMessage.group

import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.types.update.MessageUpdate

public interface PrivateMessageKey {
    public suspend fun key(
        update: MessageUpdate,
        message: PrivateContentMessage<*>
    ): Any?
}
