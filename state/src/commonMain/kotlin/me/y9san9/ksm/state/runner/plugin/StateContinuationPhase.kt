package me.y9san9.ksm.state.runner.plugin

import me.y9san9.ksm.state.StateAction
import me.y9san9.ksm.state.StateScope
import me.y9san9.ksm.state.continuation.StateContinuation
import me.y9san9.pipeline.context.build
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.docs.describe
import me.y9san9.pipeline.docs.description
import me.y9san9.pipeline.docs.subjectDescription
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable
import me.y9san9.pipeline.subject.subject
import kotlin.coroutines.*

public val StateContinuationPhase: PipelinePhase = buildPipelinePhase {
    name = "StateContinuation"

    describe {
        description = "Decorates StateAction making it possible to invoke StateScope.finish()"
        subjectDescription = listOf("StateAction - required. Action to decorate")
    }

    runnable {
        val action = subject.require(StateAction)

        subject {
            this[StateAction] = StateAction { scope ->
                suspendCoroutine { continuation ->
                    val stateContinuation = StateContinuation {
                        require(coroutineContext === continuation.context) { "Cannot finish State from another coroutine" }
                        continuation.resume(Unit)
                        waitForever()
                    }
                    val scopeContext = scope.context.build {
                        this[StateContinuation] = stateContinuation
                    }
                    suspend {
                        action.run(StateScope.of(scopeContext))
                    }.startCoroutine(continuation)
                }
            }
        }
    }
}

private suspend fun waitForever(): Nothing {
    suspendCoroutine<Nothing> { }
}
