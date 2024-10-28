package me.y9san9.ksm.telegram.privateMessage.group

import me.y9san9.ksm.telegram.TelegramFSM
import me.y9san9.ksm.telegram.group.UpdateStateGroup
import me.y9san9.ksm.telegram.group.addUpdateStateGroup
import me.y9san9.pipeline.annotation.PipelineDsl

public object PrivateMessageGroup {
    @PipelineDsl
    public class Builder : UpdateStateGroup.Builder()
}

@PipelineDsl
public inline fun TelegramFSM.Builder.privateMessage(block: PrivateMessageGroup.Builder.() -> Unit) {
    val builder = PrivateMessageGroup.Builder()
    builder.filter { true }
    builder.key { update -> update.data.chat.id.chatId.long }
    builder.storage = TelegramPrivateMessageStorage.InMemory()
    builder.block()
    addUpdateStateGroup(builder.build())
}
