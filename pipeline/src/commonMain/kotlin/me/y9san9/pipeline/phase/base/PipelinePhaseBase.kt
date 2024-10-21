package me.y9san9.pipeline.phase.base

import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.PipelinePhaseRunnable

public object PipelinePhaseBase {
    public object Config {
        public object Name : PipelineElement<String>
        public object Runnable : PipelineElement<PipelinePhaseRunnable>
    }
}
