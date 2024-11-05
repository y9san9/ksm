package me.y9san9.pipeline.context

import me.y9san9.pipeline.plugin.PipelinePlugin

public class MapPipelineContext(public val map: Map<PipelineElement<*>, *>) : PipelineContext {
    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> get(element: PipelineElement<T>): T? {
        return map[element] as T?
    }

    override fun contains(element: PipelineElement<*>): Boolean {
        return element in map && map[element] != null
    }

    override fun toString(): String {
        val entries = map.entries.joinToString(separator = ",\n") { (k, v) ->
            when {
                k !== v -> "${k.name}=$v"
                k is PipelinePlugin -> "plugin($k)"
                else -> "$k"
            }
        }
        return buildString {
            appendLine("PipelineContext[")
            appendLine(entries.prependIndent())
            append("]")
        }
    }
}
