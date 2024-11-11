package me.y9san9.ksm.telegram.transition

import me.y9san9.ksm.telegram.TelegramFSM.Plugin.Bot
import me.y9san9.ksm.telegram.TelegramFSM.Plugin.StateGroup
import me.y9san9.ksm.telegram.transition.FSMTransition.Plugin.GotoCommand
import me.y9san9.ksm.telegram.routing.StateDescriptor
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase

public val SavePhase: PipelinePhase = buildPipelinePhase {
    name = "SavePhase"

    runnable {
        val descriptor = require(GotoCommand).descriptor
        val group = require(StateGroup)

        val bot = require(Bot)

        val data = StateDescriptor.encode(descriptor)
        group.storage.save(bot, toPipelineContext(), data)
    }
}
