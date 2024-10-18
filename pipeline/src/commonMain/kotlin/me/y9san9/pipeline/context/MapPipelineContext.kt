package me.y9san9.pipeline.context

public class MapPipelineContext(public val map: Map<PipelineElement<*>, *>) : PipelineContext {
    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> get(element: PipelineElement<T>): T? {
        return map[element] as T?
    }

    override fun contains(element: PipelineElement<*>): Boolean {
        return element in map && map[element] != null
    }

    override fun toString(): String = "PipelineContext[${map.entries.joinToString(separator = ",") { (k, v) -> "${k.elementName()}=$v" }}]"
}
