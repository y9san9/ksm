package me.y9san9.ksm.telegram.callbackQuery

import me.y9san9.ksm.telegram.TelegramFSM
import me.y9san9.ksm.telegram.handler.RunPhase
import me.y9san9.ksm.telegram.handler.UpdateHandler
import me.y9san9.ksm.telegram.handler.buildUpdateHandler
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.insertPhaseBefore
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.set

public val SetupCallbackQuery: PipelinePhase = buildPipelinePhase {
    name = "SetupCallbackQuery"

    runnable {
        val defaultHandler = context[TelegramFSM.DefaultHandler]

        context[CallbackQueryPlugin.DefaultHandler] = buildUpdateHandler(defaultHandler) {
            context.set(UpdateHandler.Pipeline) {
                insertPhaseBefore(RunPhase, StartResetPhase)
            }
        }
    }
}
