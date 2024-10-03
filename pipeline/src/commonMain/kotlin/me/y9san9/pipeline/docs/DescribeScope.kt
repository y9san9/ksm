package me.y9san9.pipeline.docs

import me.y9san9.pipeline.PipelineBuilder
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.phase.PipelinePhaseBuilder

@PipelineDsl
public interface DescribeScope {
    public val context: MutablePipelineContext

    public companion object {
        public fun of(context: PipelineContext? = null): DescribeScope {
            return object : DescribeScope {
                override val context = mutablePipelineContextOf(context = context ?: PipelineContext.Empty)
                override fun toString() = "DescribeScope(context=${this.context})"
            }
        }
    }
}

public inline fun PipelineBuilder.describe(crossinline block: DescribeScope.() -> Unit) {
    describeProvider = DescribeProvider { context ->
        val scope = DescribeScope.of(context)
        block(scope)
        scope.context.toPipelineContext()
    }
}

public inline fun PipelinePhaseBuilder.describe(
    crossinline block: DescribeScope.() -> Unit
) {
    describeProvider = DescribeProvider { context ->
        val scope = DescribeScope.of(context)
        block(scope)
        scope.context.toPipelineContext()
    }
}
