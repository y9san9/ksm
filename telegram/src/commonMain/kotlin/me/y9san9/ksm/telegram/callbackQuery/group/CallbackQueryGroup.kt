package me.y9san9.ksm.telegram.callbackQuery.group

import dev.inmo.tgbotapi.extensions.utils.extensions.raw.inline_message_id
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.message
import dev.inmo.tgbotapi.utils.RiskFeature
import me.y9san9.ksm.telegram.TelegramFSM
import me.y9san9.ksm.telegram.group.UpdateStateGroup
import me.y9san9.ksm.telegram.group.addUpdateStateGroup
import me.y9san9.pipeline.annotation.PipelineDsl

public object CallbackQueryGroup {
    @PipelineDsl
    public class Builder : UpdateStateGroup.Builder()
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
    builder.storage = TelegramCallbackQueryStorage.InMemory()
    builder.block()
    addUpdateStateGroup(builder.build())
}
