package me.y9san9.ksm.fsm.looper

import me.y9san9.ksm.fsm.plugin.FSMLooperPhase
import me.y9san9.pipeline.context.PipelineElement
import kotlin.coroutines.Continuation
import kotlin.coroutines.EmptyCoroutineContext


/**
 * This class makes it possible to avoid recursion
 * by scheduling tasks to be executed during [FSMLooperPhase]
 */
public class Looper {
    private var queue = mutableListOf<Action<*>>()
    private var running = true

    public fun post(action: Action<*>) {
        if (!running) error("Can't post action on non-running Looper")
        queue += action
    }

    public fun post(block: suspend () -> Unit) {
        val emptyContinuation = object : Continuation<Unit> {
            override val context = EmptyCoroutineContext
            override fun resumeWith(result: Result<Unit>) { result.getOrThrow() }
        }
        val action = Action(block, emptyContinuation)
        post(action)
    }

    public suspend fun runLoop() {
        while (true) {
            if (queue.isEmpty()) break
            val actions = queue.toList()
            queue.clear()
            for (action in actions) {
                handleAction(action)
            }
        }
        running = false
    }

    private suspend fun <T> handleAction(action: Action<T>) {
        with(action) {
            val result = runCatching { block() }
            continuation.resumeWith(result)
        }
    }

    public class Action<T>(
        public val block: suspend () -> T,
        public val continuation: Continuation<T>
    )

    public companion object : PipelineElement<Looper>
}
