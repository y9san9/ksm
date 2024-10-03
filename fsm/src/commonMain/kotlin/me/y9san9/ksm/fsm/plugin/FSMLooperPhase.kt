package me.y9san9.ksm.fsm.plugin

import me.y9san9.ksm.fsm.looper.Looper
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.docs.contextDescription
import me.y9san9.pipeline.docs.describe
import me.y9san9.pipeline.docs.description
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable

public val FSMLooperPhase: PipelinePhase = buildPipelinePhase {
    name = "FSMLooper"

    describe {
        description = "Runs Actions that are posted to Looper"
        contextDescription = listOf("Looper - the looper to start actions on")
    }

    runnable {
        val looper = context.require(Looper)
        looper.runLoop()
    }
}
