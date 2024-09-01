package me.y9san9.ksm.route

import me.y9san9.pipeline.context.PipelineContext

public class PipelineRouteList(
    public val initial: PipelineContext,
    public val list: List<PipelineContext>
)
