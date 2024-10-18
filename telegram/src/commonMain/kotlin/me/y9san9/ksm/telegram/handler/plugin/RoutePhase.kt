package me.y9san9.ksm.telegram.handler.plugin

import me.y9san9.ksm.router.plugin.StateRouterBase
import me.y9san9.ksm.telegram.handler.plugin.TelegramHandlerBase.Subject
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable
import me.y9san9.pipeline.subject.setSubject
import me.y9san9.pipeline.subject.subject

public val RoutePhase: PipelinePhase = buildPipelinePhase {
    name = "RoutePhase"

    runnable {
        val router = subject.require(Subject.Router)

        setSubject(StateRouterBase.Subject.StateList, from = Subject.StateList)
        setSubject(StateRouterBase.Subject.Descriptor, from = Subject.Descriptor)

        subject = router.proceed(subject)

        setSubject(Subject.State, from = StateRouterBase.Subject.State)
    }
}
