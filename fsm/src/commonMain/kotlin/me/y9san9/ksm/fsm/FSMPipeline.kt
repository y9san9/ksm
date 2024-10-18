package me.y9san9.ksm.fsm

import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.proceed

public data object FSMPipeline : PipelineElement<Pipeline>

public suspend fun FSM.run(subject: PipelineContext = PipelineContext.Empty): PipelineContext {
    return context.require(FSMPipeline).proceed(context, subject)
}
