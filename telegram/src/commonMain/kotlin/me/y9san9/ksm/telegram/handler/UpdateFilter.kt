package me.y9san9.ksm.telegram.handler

import dev.inmo.tgbotapi.types.update.abstracts.Update

public fun interface UpdateFilter {
    public suspend fun filter(update: Update): Boolean
}
