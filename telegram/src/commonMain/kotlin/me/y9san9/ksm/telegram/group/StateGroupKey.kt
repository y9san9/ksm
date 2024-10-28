package me.y9san9.ksm.telegram.group

import dev.inmo.tgbotapi.types.update.abstracts.Update
import me.y9san9.ksm.telegram.group.base.UpdateGroupBase

public fun interface StateGroupKey {
    public suspend fun key(update: Update): Any?
}

public val UpdateStateGroup.key: StateGroupKey?
    get() = context[UpdateGroupBase.Config.Key]
