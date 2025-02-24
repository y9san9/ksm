package me.y9san9.pipeline.plugin

import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.set

public fun MutablePipelineContext.install(
    plugin: PipelinePlugin
) {
    if (!installOnce(plugin)) {
        error("Cannot install plugin '${plugin.name}' twice")
    }
}

public fun MutablePipelineContext.installOnce(
    plugin: PipelinePlugin
): Boolean {
    if (plugin in context) return false
    this[plugin] = Unit
    plugin.apply(context = this)
    return true
}
