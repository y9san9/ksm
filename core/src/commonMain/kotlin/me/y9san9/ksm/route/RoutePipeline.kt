package me.y9san9.ksm.route

import me.y9san9.pipeline._PipelineRunnable

public object RoutePipeline : Pipeline {
    override val name: String = "Route"

    override val phases: List<_PipelineRunnable> = listOf(
        _PipelineRunnable.Start,
        InitialRoutePhase,
        _PipelineRunnable.Finish
    )
}
