package me.y9san9.pipeline.phase.base

import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.phase.PipelinePhaseRunnable
import me.y9san9.pipeline.plugin.PipelinePlugin

public object PipelinePhaseBase : PipelinePlugin {
    override val name: String = "PipelinePhaseBase"

    public val Name: PipelineElement<String> by PipelineElement
    public val Runnable: PipelineElement<PipelinePhaseRunnable> by PipelineElement

    override fun apply(context: MutablePipelineContext) {}
}
