package me.y9san9.pipeline.plugin

import me.y9san9.pipeline.context.PipelineContext

public inline fun PipelineContext.dependsOn(
    plugin: PipelinePlugin,
    message: () -> String = { "Plugin '${plugin.name}' is required to continue. Debug info: $this" }
) {
    if (plugin !in this) error(message())
}
