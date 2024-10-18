package me.y9san9.ksm.telegram.handler.plugin

import me.y9san9.ksm.state.plugin.StateBase
import me.y9san9.ksm.state.runner.plugin.StateRunnerBase
import me.y9san9.ksm.state.runner.proceed
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable
import me.y9san9.pipeline.subject.setSubject
import me.y9san9.pipeline.subject.subject

public val RunPhase: PipelinePhase = buildPipelinePhase {
    name = "RunPhase"

    runnable {
        val runner = subject.require(TelegramHandlerBase.Subject.Runner)
        val state = subject.require(TelegramHandlerBase.Subject.State)

        val stateAction = state.context.require(StateBase.Config.Action)

        setSubject(StateRunnerBase.Subject.StateAction, stateAction)

        subject = runner.proceed(subject)
    }
}
