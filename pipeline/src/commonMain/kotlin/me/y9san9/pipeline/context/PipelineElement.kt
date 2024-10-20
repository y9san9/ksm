package me.y9san9.pipeline.context

public interface PipelineElement<@Suppress("unused") T : Any> {
    public val name: String get() = toString()
}
