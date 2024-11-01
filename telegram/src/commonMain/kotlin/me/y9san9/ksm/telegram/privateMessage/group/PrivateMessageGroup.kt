package me.y9san9.ksm.telegram.privateMessage.group

import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.types.update.MessageUpdate
import me.y9san9.aqueue.AQueue
import me.y9san9.ksm.telegram.TelegramFSM
import me.y9san9.ksm.telegram.group.*
import me.y9san9.ksm.telegram.group.base.UpdateStateGroupBase
import me.y9san9.ksm.telegram.privateMessage.routing.PrivateMessageRouting
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.set

public object PrivateMessageGroup {
    @PipelineDsl
    public class Builder {
        public val update: UpdateStateGroup.Builder = UpdateStateGroup.Builder()

        public var aqueue: AQueue? by update::aqueue

        @PipelineDsl
        public inline fun key(crossinline block: suspend (MessageUpdate) -> Any?) {
            update.key = UpdateKey { update -> block(update as MessageUpdate) }
        }

        @PipelineDsl
        public inline fun filter(crossinline block: (MessageUpdate) -> Boolean) {
            update.filter = UpdateFilter { update ->
                update is MessageUpdate && update.data is PrivateContentMessage<*> && block(update)
            }
        }

        @PipelineDsl
        public var storage: PrivateMessageStorage
            @Deprecated("use setter", level = DeprecationLevel.HIDDEN)
            get() = TODO("use setter")
            set(value) { update.storage = value.toUpdateStorage() }

        @PipelineDsl
        public fun routing(block: PrivateMessageRouting.() -> Unit) {
            val routing = PrivateMessageRouting()
            routing.block()
            update.stateList = routing.update.toStateList()
        }
    }
}

@PipelineDsl
public inline fun TelegramFSM.Builder.privateMessage(block: PrivateMessageGroup.Builder.() -> Unit) {
    val builder = PrivateMessageGroup.Builder()
    builder.filter { true }
    builder.key { update -> update.data.chat.id.chatId.long }
    builder.storage = PrivateMessageStorage.InMemory()
    builder.block()
    addUpdateStateGroup(builder.update.build())
}
