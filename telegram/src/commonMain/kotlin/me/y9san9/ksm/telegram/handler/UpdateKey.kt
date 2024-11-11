package me.y9san9.ksm.telegram.handler

import dev.inmo.tgbotapi.types.update.abstracts.Update

public fun interface UpdateKey {
    public suspend fun key(update: Update): Any?
}
