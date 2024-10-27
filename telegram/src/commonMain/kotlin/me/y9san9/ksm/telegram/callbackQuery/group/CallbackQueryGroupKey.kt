package me.y9san9.ksm.telegram.callbackQuery.group

import dev.inmo.tgbotapi.types.update.CallbackQueryUpdate
import dev.inmo.tgbotapi.types.update.MessageUpdate
import me.y9san9.ksm.telegram.group.StateGroupKey
import me.y9san9.ksm.telegram.group.base.UpdateGroupBase
import me.y9san9.pipeline.context.set

public inline fun CallbackQueryGroup.Builder.key(crossinline block: suspend (CallbackQueryUpdate) -> Any?) {
    context[UpdateGroupBase.Config.Key] = StateGroupKey { update -> block(update as CallbackQueryUpdate) }
}
