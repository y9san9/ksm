package me.y9san9.ksm.state.runner

import me.y9san9.ksm.state.State
import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.PipelineBuilder
import me.y9san9.pipeline.buildPipeline
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.proceed

public object StateRunnerPipeline : PipelineElement<Pipeline>

public suspend fun StateRunner.proceed(subject: PipelineContext): PipelineContext {
    return context.require(StateRunnerPipeline).proceed(context, subject)
}

public inline fun StateRunnerBuilder.pipeline(block: PipelineBuilder.() -> Unit) {
    context[StateRunnerPipeline] = buildPipeline(context[StateRunnerPipeline], block)
}
