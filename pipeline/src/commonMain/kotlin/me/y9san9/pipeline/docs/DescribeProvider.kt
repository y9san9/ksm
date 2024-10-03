package me.y9san9.pipeline.docs

import me.y9san9.pipeline.PipelineBuilder
import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.phase.PipelinePhaseBuilder

public fun interface DescribeProvider {
    /**
     * @return is [PipelineContext] and not [PipelineDocs] because you
     *  may want to add middleware that inserts incomplete state in context
     */
    public fun describe(context: PipelineContext): PipelineContext

    public companion object : PipelineElement<DescribeProvider>
}

public var PipelineBuilder.describeProvider: DescribeProvider
    get() = context[DescribeProvider] ?: DescribeProvider { it }
    set(value) { context[DescribeProvider] = value }

public var PipelinePhaseBuilder.describeProvider: DescribeProvider
    get() = context[DescribeProvider] ?: DescribeProvider { it }
    set(value) { context[DescribeProvider] = value }
