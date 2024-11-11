package me.y9san9.pipeline.phase

import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.build
import me.y9san9.pipeline.context.mutablePipelineContextOf
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.plugin.PipelinePlugin
import me.y9san9.pipeline.plugin.install

public data class PipelinePhase(public val context: PipelineContext) {
    public val name: String get() = context.require(Name)
    public val runnable: PipelinePhaseRunnable get() = context.require(Runnable)

    init {
        context.require(Name) { "Please provide PipelinePhase name" }
    }

    public class Builder(context: PipelineContext) {
        public val context: MutablePipelineContext = mutablePipelineContextOf(context)

        public var name: String
            get() = context.require(Name)
            set(value) { context[Name] = value }

        public fun runnable(block: suspend MutablePipelineContext.() -> Unit) {
            context[Runnable] = object : PipelinePhaseRunnable {
                override suspend fun proceed(context: PipelineContext): PipelineContext {
                    return context.build { block() }
                }
                override fun toString() = "PipelinePhaseRunnable { /* code */ }"
            }
        }

        public constructor() : this(PipelineContext.Empty) {
            context.install(PipelinePhase)
        }

        public fun build(): PipelinePhase {
            return PipelinePhase(context.toPipelineContext())
        }
    }

    public companion object : PipelinePlugin {
        override val name: String = "PipelinePhase"

        public val Name: PipelineElement<String> by PipelineElement
        public val Runnable: PipelineElement<PipelinePhaseRunnable> by PipelineElement

        override fun apply(context: MutablePipelineContext) {
            context[Runnable] = object : PipelinePhaseRunnable {
                override suspend fun proceed(context: PipelineContext): PipelineContext {
                    return context
                }
                override fun toString() = "PipelinePhaseRunnable { /* NO OP */ }"
            }
        }
    }
}

public inline fun buildPipelinePhase(
    from: PipelinePhase? = null,
    block: PipelinePhase.Builder.() -> Unit
): PipelinePhase {
    val builder = when (from) {
        null -> PipelinePhase.Builder()
        else -> PipelinePhase.Builder(from.context)
    }
    builder.apply(block)
    return builder.build()
}
