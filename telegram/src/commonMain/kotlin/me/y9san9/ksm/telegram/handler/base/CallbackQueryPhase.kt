package me.y9san9.ksm.telegram.handler.base

import dev.inmo.tgbotapi.extensions.utils.extensions.raw.inline_message_id
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.message
import dev.inmo.tgbotapi.types.update.CallbackQueryUpdate
import dev.inmo.tgbotapi.utils.RiskFeature
import me.y9san9.ksm.telegram.handler.base.TelegramUpdateHandlerBase.Subject
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable
import me.y9san9.pipeline.proceed

@OptIn(RiskFeature::class)
public val CallbackQueryPhase: PipelinePhase = buildPipelinePhase {
    name = "CallbackQueryPhase"

    runnable {
        val update = context.require(Subject.Update) as? CallbackQueryUpdate ?: return@runnable
        context[Subject.CallbackQueryUpdate] = update
        context[Subject.CallbackQueryMessageId] = update.data.message?.messageId
        context[Subject.CallbackQueryInlineMessageId] = update.data.inline_message_id
        takeFrom(context.require(Subject.CallbackQuery).proceed(context))
    }
}
