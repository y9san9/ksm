package me.y9san9.ksm.telegram.messages

import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.types.update.MessageUpdate
import me.y9san9.ksm.telegram.TelegramFSM
import me.y9san9.ksm.telegram.group.*
import me.y9san9.pipeline.annotation.PipelineDsl

@PipelineDsl
public inline fun TelegramFSM.Builder.messages(block: StateGroup.Builder.() -> Unit) {
    stateGroup {
        filter { update -> update is MessageUpdate }
        key { update -> (update.data as PrivateContentMessage<*>).from.id }
        storage = TelegramStorage.InMemory()
        block()
    }
}
