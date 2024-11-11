package me.y9san9.pipeline.context

public class ProxyMutablePipelineContext(
    private val element: PipelineElement<PipelineContext>,
    private val upstream: MutablePipelineContext
) : MutablePipelineContext {
    override fun toPipelineContext(): PipelineContext {
        @Suppress("USELESS_CAST") // Useful to call get function and not the overload
        return (upstream as PipelineContext)[element] ?: PipelineContext.Empty
    }
    override fun takeFrom(context: PipelineContext) {
        upstream[element] = context
    }
}
