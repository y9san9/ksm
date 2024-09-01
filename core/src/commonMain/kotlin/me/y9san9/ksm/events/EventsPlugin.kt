package me.y9san9.ksm.events

import me.y9san9.aqueue.AQueue
import me.y9san9.ksm.state.StatePlugin
import me.y9san9.pipeline.builder.PipelineBuilder
import me.y9san9.pipeline.builder.withPipeline
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.plugin.PipelinePlugin

public object EventsPlugin : PipelinePlugin {
    override fun apply(builder: PipelineBuilder) {
        with(builder) {
            insertPhaseAfter(PipelinePhase.Start.name, EventsPipelinePhase)

            withPipeline(Pipeline)
            withPipeline(KeyPipeline)
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
