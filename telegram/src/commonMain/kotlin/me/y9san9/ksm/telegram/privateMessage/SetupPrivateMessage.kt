package me.y9san9.ksm.telegram.privateMessage

import me.y9san9.ksm.telegram.TelegramFSM
import me.y9san9.ksm.telegram.handler.RunPhase
import me.y9san9.ksm.telegram.handler.UpdateHandler
import me.y9san9.ksm.telegram.handler.buildUpdateHandler
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.insertPhaseBefore
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.set

public val SetupPrivateMessage: PipelinePhase = buildPipelinePhase {
    name = "SetupPrivateMessage"

    runnable {
        val defaultHandler = context[TelegramFSM.DefaultHandler]

        context[PrivateMessagePlugin.DefaultHandler] = buildUpdateHandler(defaultHandler) {
            context.set(UpdateHandler.Pipeline) {
                insertPhaseBefore(RunPhase, StartResetPhase)
            }
        }
    }
}
