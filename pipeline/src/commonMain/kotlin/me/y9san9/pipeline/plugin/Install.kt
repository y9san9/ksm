package me.y9san9.pipeline.plugin

import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.set

public fun MutablePipelineContext.install(
    plugin: PipelinePlugin
) {
    if (plugin in context) error("Cannot install plugin '${plugin.name}' twice")
    this[plugin] = Unit
    plugin.apply(context = this)
}
