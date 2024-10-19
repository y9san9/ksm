package me.y9san9.ksm.telegram.handler.plugin

import me.y9san9.ksm.router.plugin.StateRouterBase
import me.y9san9.ksm.state.StateTransition
import me.y9san9.ksm.state.build
import me.y9san9.ksm.state.plugin.StateBase
import me.y9san9.ksm.telegram.handler.plugin.TelegramHandlerBase.Subject
import me.y9san9.pipeline.context.plus
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable
import me.y9san9.pipeline.subject.setSubject
import me.y9san9.pipeline.subject.subject

public val NavigatePhase: PipelinePhase = buildPipelinePhase {
    name = "NavigatePhase"

    runnable {
        val router = subject.require(Subject.Router)

        val descriptor = subject[Subject.Descriptor] ?: return@runnable
        setSubject(StateRouterBase.Subject.Descriptor, descriptor)

        subject = router.proceed(subject)

        val state = subject.require(StateRouterBase.Subject.State).build {
            context.subject += subject
        }

        val transition = state.context.require(StateBase.Config.Transition)

        val scope = StateTransition.Scope(context)

        transition.run(scope)
    }
}
