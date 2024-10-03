package me.y9san9.ksm.fsm

import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.PipelineBuilder
import me.y9san9.pipeline.buildPipeline
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.proceed

public data object FSMPipeline : PipelineElement<Pipeline>

public suspend fun FSM.run() {
    context.require(FSMPipeline).proceed(context, PipelineContext.Empty)
}

public inline fun FSMBuilder.pipeline(block: PipelineBuilder.() -> Unit = {}) {
    context[FSMPipeline] = buildPipeline(context[FSMPipeline], block)
}
