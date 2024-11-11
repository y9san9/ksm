package me.y9san9.ksm.telegram.routing

import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.mutablePipelineContextOf
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.insertPhaseLast
import me.y9san9.pipeline.plugin.PipelinePlugin
import me.y9san9.pipeline.plugin.install
import me.y9san9.pipeline.set

public data class FSMRouter(public val context: PipelineContext) {

    public val pipeline: Pipeline get() = context.require(Pipeline)

    @PipelineDsl
    public class Builder(context: PipelineContext) {
        public val context: MutablePipelineContext = mutablePipelineContextOf(context)

        public constructor() : this(PipelineContext.Empty) {
            context.install(FSMRouter)
        }

        public fun build(): FSMRouter {
            return FSMRouter(context.toPipelineContext())
        }
    }

    public companion object Plugin : PipelinePlugin {
        override val name: String = "FSMRouter"

        public val Pipeline: PipelineElement<Pipeline> by PipelineElement

        override fun apply(context: MutablePipelineContext) {
            context.set(Pipeline) {
                insertPhaseLast(FindByNamePhase)
            }
        }
    }
}

public inline fun buildFSMRouter(
    from: FSMRouter? = null,
    block: FSMRouter.Builder.() -> Unit = {}
): FSMRouter {
    val builder = when (from) {
        null -> FSMRouter.Builder()
        else -> FSMRouter.Builder(from.context)
    }
    builder.block()
    return builder.build()
}
