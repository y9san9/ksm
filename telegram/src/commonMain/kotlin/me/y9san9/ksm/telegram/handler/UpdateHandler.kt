package me.y9san9.ksm.telegram.handler

import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.mutablePipelineContextOf
import me.y9san9.pipeline.insertPhaseLast
import me.y9san9.pipeline.plugin.PipelinePlugin
import me.y9san9.pipeline.plugin.install
import me.y9san9.pipeline.set

public data class UpdateHandler(public val context: PipelineContext) {
    public class Builder(context: PipelineContext) {
        public val context: MutablePipelineContext = mutablePipelineContextOf(context)

        public constructor() : this(PipelineContext.Empty) {
            context.install(Plugin)
        }

        public fun build(): UpdateHandler {
            return UpdateHandler(context.toPipelineContext())
        }
    }

    public companion object Plugin : PipelinePlugin {
        override val name: String = "UpdateHandler"

        public val Pipeline: PipelineElement<Pipeline> by PipelineElement

        override fun apply(context: MutablePipelineContext) {
            context.set(Pipeline) {
                insertPhaseLast(RestorePhase)
                insertPhaseLast(HandlerRoutingPhase)
                insertPhaseLast(RunPhase)
                insertPhaseLast(HandlerTransitionPhase)
            }
        }
    }
}

public inline fun buildUpdateHandler(
    from: UpdateHandler? = null,
    block: UpdateHandler.Builder.() -> Unit = {}
): UpdateHandler {
    val builder = when (from) {
        null -> UpdateHandler.Builder()
        else -> UpdateHandler.Builder(from.context)
    }
    builder.block()
    return builder.build()
}

public fun MutablePipelineContext.set(
    element: PipelineElement<UpdateHandler>,
    block: UpdateHandler.Builder.() -> Unit
): UpdateHandler {
    return buildUpdateHandler(context[element], block)
}
