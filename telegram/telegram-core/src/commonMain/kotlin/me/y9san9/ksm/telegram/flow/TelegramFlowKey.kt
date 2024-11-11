package me.y9san9.ksm.telegram.flow

import dev.inmo.tgbotapi.types.update.abstracts.Update

public fun interface TelegramFlowKey {
    public suspend fun key(update: Update): Any?
}
