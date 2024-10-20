package me.y9san9.ksm.telegram.handler.base

import dev.inmo.tgbotapi.extensions.utils.extensions.raw.text
import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.utils.RiskFeature
import me.y9san9.ksm.telegram.handler.base.TelegramUpdateHandlerBase.Subject
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable

@OptIn(RiskFeature::class)
public val StartResetPhase: PipelinePhase = buildPipelinePhase {
    name = "StartResetPhase"

    runnable {
        val update = context.require(Subject.Update)
        val message = update.data
        if (message !is PrivateContentMessage<*>) return@runnable
        if (message.text == "/start") {
            context[Subject.RestoredDescriptor] = null
        }
    }
}
