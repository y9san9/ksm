package me.y9san9.ksm.throwable

import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.PipelineElement

public interface StateThrowableHandler {
    public fun handle(
        context: PipelineContext,
        throwable: Throwable
    )

    public object Throw : StateThrowableHandler {
        override fun handle(
            context: PipelineContext,
            throwable: Throwable
        ) {
            throw throwable
        }
    }

    public companion object : PipelineElement<StateThrowableHandler>
}
