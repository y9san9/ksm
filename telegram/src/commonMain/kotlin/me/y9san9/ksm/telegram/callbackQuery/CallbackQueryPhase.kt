package me.y9san9.ksm.telegram.callbackQuery

import dev.inmo.tgbotapi.extensions.utils.extensions.raw.inline_message_id
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.message
import dev.inmo.tgbotapi.types.update.CallbackQueryUpdate
import dev.inmo.tgbotapi.utils.RiskFeature
import me.y9san9.ksm.telegram.TelegramFSM.Plugin.Update
import me.y9san9.ksm.telegram.callbackQuery.CallbackQueryPlugin.InlineMessageId
import me.y9san9.ksm.telegram.callbackQuery.CallbackQueryPlugin.MessageId
import me.y9san9.ksm.telegram.callbackQuery.CallbackQueryPlugin.Pipeline
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.proceedIn

@OptIn(RiskFeature::class)
public val CallbackQueryPhase: PipelinePhase = buildPipelinePhase {
    name = "CallbackQueryPhase"

    runnable {
        val update = context.require(Update) as? CallbackQueryUpdate ?: return@runnable
        context[CallbackQueryPlugin.Update] = update
        context[MessageId] = update.data.message?.messageId
        context[InlineMessageId] = update.data.inline_message_id
        context.require(Pipeline).proceedIn(context)
    }
}
