package me.y9san9.pipeline.context

import me.y9san9.pipeline.plugin.PipelinePlugin

public interface PipelineElement<@Suppress("unused") T> {
    public fun elementName(): String {
        if (this is PipelinePlugin) { return name }
        var qualifiedName = this::class.qualifiedName ?: return toString()

        val firstUpperCase = qualifiedName.indexOfFirst { char -> char.isUpperCase() }
        if (firstUpperCase == -1) return qualifiedName

        qualifiedName = qualifiedName.substring(firstUpperCase)

        return qualifiedName.removeSuffix(".Companion")
    }
}
