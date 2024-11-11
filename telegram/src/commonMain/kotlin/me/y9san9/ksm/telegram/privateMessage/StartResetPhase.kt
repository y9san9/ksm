package me.y9san9.ksm.telegram.privateMessage

import dev.inmo.tgbotapi.extensions.utils.extensions.raw.text
import dev.inmo.tgbotapi.utils.RiskFeature
import me.y9san9.ksm.telegram.TelegramFSM.Plugin.StateDescriptor
import me.y9san9.ksm.telegram.privateMessage.PrivateMessagePlugin.Message
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase

@OptIn(RiskFeature::class)
public val StartResetPhase: PipelinePhase = buildPipelinePhase {
    name = "StartResetPhase"

    runnable {
        val message = context.require(Message)
        if (message.text == "/start") {
            context[StateDescriptor] = null
        }
    }
}
