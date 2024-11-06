package me.y9san9.ksm.telegram.plugin.handler.privateMessage

import dev.inmo.tgbotapi.extensions.utils.extensions.raw.text
import dev.inmo.tgbotapi.utils.RiskFeature
import me.y9san9.ksm.telegram.plugin.handler.privateMessage.PrivateMessagePlugin.Message
import me.y9san9.ksm.telegram.state.base.UpdateStateBase.Descriptor
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable

@OptIn(RiskFeature::class)
public val PrivateMessageStartResetPhase: PipelinePhase = buildPipelinePhase {
    name = "PrivateMessageStartResetPhase"

    runnable {
        val message = context.require(Message)
        if (message.text == "/start") {
            context[Descriptor] = null
        }
    }
}
