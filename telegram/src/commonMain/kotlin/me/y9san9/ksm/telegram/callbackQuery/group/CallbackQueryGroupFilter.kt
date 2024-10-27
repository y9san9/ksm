package me.y9san9.ksm.telegram.callbackQuery.group

import dev.inmo.tgbotapi.types.update.CallbackQueryUpdate
import me.y9san9.ksm.telegram.group.StateGroupFilter
import me.y9san9.ksm.telegram.group.base.UpdateGroupBase
import me.y9san9.pipeline.context.set

public inline fun CallbackQueryGroup.Builder.filter(crossinline block: (CallbackQueryUpdate) -> Boolean) {
    context[UpdateGroupBase.Config.Filter] = StateGroupFilter { update ->
        update is CallbackQueryUpdate && block(update)
    }
}
