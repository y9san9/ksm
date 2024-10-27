package me.y9san9.ksm.telegram.privateMessage.group

import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.types.update.MessageUpdate
import me.y9san9.ksm.telegram.group.StateGroupFilter
import me.y9san9.ksm.telegram.group.base.UpdateGroupBase
import me.y9san9.pipeline.context.set

public inline fun PrivateMessageGroup.Builder.filter(crossinline block: (MessageUpdate) -> Boolean) {
    context[UpdateGroupBase.Config.Filter] = StateGroupFilter { update ->
        update is MessageUpdate && update.data is PrivateContentMessage<*> && block(update)
    }
}
