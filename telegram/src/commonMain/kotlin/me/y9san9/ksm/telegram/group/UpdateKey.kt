package me.y9san9.ksm.telegram.group

import dev.inmo.tgbotapi.types.update.abstracts.Update
import me.y9san9.ksm.telegram.group.base.UpdateStateGroupBase

public fun interface UpdateKey {
    public suspend fun key(update: Update): Any?
}

public val UpdateStateGroup.key: UpdateKey?
    get() = context[UpdateStateGroupBase.Config.Key]
