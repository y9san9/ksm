package me.y9san9.pipeline.plugin

import me.y9san9.pipeline.context.PipelineContext

public fun PipelineContext.dependsOn(plugin: PipelinePlugin) {
    if (plugin !in this) error("Plugin '${plugin.name}' is required to continue")
}
