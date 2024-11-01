package me.y9san9.ksm.telegram.callbackQuery.group

import dev.inmo.tgbotapi.extensions.utils.extensions.raw.inline_message_id
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.message
import dev.inmo.tgbotapi.types.update.CallbackQueryUpdate
import dev.inmo.tgbotapi.utils.RiskFeature
import me.y9san9.aqueue.AQueue
import me.y9san9.ksm.telegram.TelegramFSM
import me.y9san9.ksm.telegram.callbackQuery.routing.CallbackQueryRouting
import me.y9san9.ksm.telegram.group.UpdateFilter
import me.y9san9.ksm.telegram.group.UpdateKey
import me.y9san9.ksm.telegram.group.UpdateStateGroup
import me.y9san9.ksm.telegram.group.addUpdateStateGroup
import me.y9san9.pipeline.annotation.PipelineDsl

public object CallbackQueryGroup {

    @PipelineDsl
    public class Builder {
        public val update: UpdateStateGroup.Builder = UpdateStateGroup.Builder()

        public var aqueue: AQueue? by update::aqueue

        @PipelineDsl
        public inline fun key(crossinline block: suspend (CallbackQueryUpdate) -> Any?) {
            update.key = UpdateKey { update ->
                block(update as CallbackQueryUpdate)
            }
        }

        @PipelineDsl
        public inline fun filter(crossinline block: (CallbackQueryUpdate) -> Boolean) {
            update.filter = UpdateFilter { update ->
                update is CallbackQueryUpdate && block(update)
            }
        }

        @PipelineDsl
        public var storage: CallbackQueryStorage
            @Deprecated("use setter", level = DeprecationLevel.HIDDEN)
            get() = TODO("use setter")
            set(value) { update.storage = value.toUpdateStorage() }

        @PipelineDsl
        public fun routing(block: CallbackQueryRouting.() -> Unit) {
            val routing = CallbackQueryRouting()
            routing.block()
            update.stateList = routing.update.toStateList()
        }
    }
}

@OptIn(RiskFeature::class)
@PipelineDsl
public inline fun TelegramFSM.Builder.callbackQuery(block: CallbackQueryGroup.Builder.() -> Unit) {
    val builder = CallbackQueryGroup.Builder()
    builder.filter { true }
    builder.key { update ->
        update.data.message?.messageId?.long
            ?: update.data.inline_message_id
    }
    builder.storage = CallbackQueryStorage.InMemory()
    builder.block()
    addUpdateStateGroup(builder.update.build())
}
