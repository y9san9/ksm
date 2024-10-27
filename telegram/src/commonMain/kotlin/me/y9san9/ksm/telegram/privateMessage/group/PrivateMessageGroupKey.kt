package me.y9san9.ksm.telegram.privateMessage.group

import dev.inmo.tgbotapi.types.update.MessageUpdate
import me.y9san9.ksm.telegram.group.StateGroupKey
import me.y9san9.ksm.telegram.group.base.UpdateGroupBase
import me.y9san9.pipeline.context.set

public inline fun PrivateMessageGroup.Builder.key(crossinline block: suspend (MessageUpdate) -> Any?) {
    context[UpdateGroupBase.Config.Key] = StateGroupKey { update -> block(update as MessageUpdate) }
}
