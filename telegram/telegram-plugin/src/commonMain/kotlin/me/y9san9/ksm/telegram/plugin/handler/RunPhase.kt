package me.y9san9.ksm.telegram.plugin.handler

import me.y9san9.ksm.telegram.plugin.routing.RoutingPlugin.SelectedState
import me.y9san9.ksm.telegram.plugin.state.StatePlugin.Action
import me.y9san9.ksm.telegram.plugin.transition.GotoContinuation
import me.y9san9.ksm.telegram.plugin.transition.GotoPlugin.Continuation
import me.y9san9.ksm.telegram.state.base.UpdateStateBase.Descriptor
import me.y9san9.ksm.telegram.state.base.UpdateStateBase.GotoCommand
import me.y9san9.ksm.telegram.state.base.UpdateStateBase.Handler
import me.y9san9.ksm.telegram.state.routing.GotoCommand
import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable
import kotlin.coroutines.resume
import kotlin.coroutines.startCoroutine
import kotlin.coroutines.suspendCoroutine

public val RunPhase: PipelinePhase = buildPipelinePhase {
    name = "RunPhase"

    runnable {
        val state = context.require(SelectedState)
        val stateAction = state[Action]

        if (stateAction != null) {
            suspendCoroutine { continuation ->
                val stateContinuation = GotoContinuation { finishSubject ->
                    context += finishSubject
                    continuation.resume(Unit)
                    waitForever()
                }
                val subject = build {
                    context[Continuation] = stateContinuation
                }
                suspend { stateAction.invoke(subject) }.startCoroutine(continuation)
            }
        }
    }
}

private suspend fun waitForever(): Nothing {
    suspendCoroutine<Nothing> {  }
}
