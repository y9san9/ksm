package me.y9san9.ksm.state

import me.y9san9.ksm.logger.*
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.with
import me.y9san9.pipeline.phase.PipelinePhase
import kotlin.coroutines.*

public object StateRunPhase : PipelinePhase {
    override val name: String = "StateRun"

    override suspend fun proceed(context: PipelineContext): PipelineContext {
        return suspendCoroutine { continuation ->
            val goto = Goto { gotoContext ->
                suspendCoroutine {
                    val childContinuation = object : Continuation<PipelineContext> {
                        override val context = continuation.context

                        override fun resumeWith(result: Result<PipelineContext>) {
                            result.onFailure {  throwable ->
                                continuation.resumeWithException(throwable)
                            }.onSuccess {
                                continuation.resume(context)
                            }
                        }
                    }

                    val childCoroutine = suspend {
                        val pipeline = context.require(StatePlugin.GotoPipeline)
                        val tagged = context.withTag("Goto")
                        tagged.log(pipeline.prettyString())
                        pipeline.proceed(gotoContext.withLogger(tagged.logger))
                    }

                    childCoroutine.startCoroutine(childContinuation)
                }
            }

            val stateContinuation = object : Continuation<Unit> {
                override val context = continuation.context
                override fun resumeWith(result: Result<Unit>) {
                    result.onFailure {  throwable ->
                        continuation.resumeWithException(throwable)
                    }.onSuccess {
                        continuation.resume(context)
                    }
                }
            }

            val stateCoroutine = suspend {
                val state = context.require(StatePlugin.StateRunnable)
                state.run(context.with(StatePlugin.Goto, goto))
            }

            stateCoroutine.startCoroutine(stateContinuation)
        }
    }
}
