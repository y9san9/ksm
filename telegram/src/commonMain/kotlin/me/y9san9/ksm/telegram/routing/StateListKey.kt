package me.y9san9.ksm.telegram.routing

import dev.inmo.tgbotapi.types.update.abstracts.Update

public interface StateListKey {
    public suspend fun key(update: Update): Any?
}
