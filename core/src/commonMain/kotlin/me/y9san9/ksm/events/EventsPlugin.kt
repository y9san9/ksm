package me.y9san9.ksm.events

import me.y9san9.aqueue.AQueue
import me.y9san9.pipeline.PipelineBuilder
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline._PipelineRunnable

public object EventsPlugin : PipelinePlugin {
    override fun apply(builder: PipelineBuilder) {
        with(builder) {
            insertPhaseAfter(_PipelineRunnable.Start.name, EventsPipelinePhase)

            context.withPipeline(Pipeline)
            context.withPipeline(KeyPipeline)
        }
    }

    public object Flow : PipelineElement<kotlinx.coroutines.flow.Flow<PipelineContext>>
    public object Pipeline : PipelineElement<me.y9san9.pipeline.Pipeline>
    public object KeyPipeline : PipelineElement<me.y9san9.pipeline.Pipeline>
    public object Event : PipelineElement<Any?>
    public object Key : PipelineElement<Any?>
    public object Queue : PipelineElement<AQueue>
    public object CoroutineContext : PipelineElement<kotlin.coroutines.CoroutineContext>
}
