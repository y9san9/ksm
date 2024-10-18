package me.y9san9.pipeline.plugin

import me.y9san9.pipeline.context.MutablePipelineContext

public inline fun <T : PipelinePlugin> MutablePipelineContext.install(
    plugin: T,
    block: T.() -> Unit
) {
    install(plugin)
    block(plugin)
}
