package me.y9san9.ksm.telegram

import me.y9san9.ksm.telegram.flow.TelegramFlow
import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.mutablePipelineContextOf
import me.y9san9.pipeline.plugin.PipelinePlugin
import me.y9san9.pipeline.plugin.install

// todo: try rule to never set something in install asides of pipeline,
//  but do special SetupXPhase
public class TelegramFSM(public val context: PipelineContext) {

    @PipelineDsl
    public class Builder(context: PipelineContext) {
        public val context: MutablePipelineContext = mutablePipelineContextOf(context)

        public constructor() : this(PipelineContext.Empty) {
            context.install(TelegramFSM)
        }

        public fun build(): TelegramFSM {
            return TelegramFSM(context.toPipelineContext())
        }
    }

    public companion object Plugin : PipelinePlugin {
        override val name: String = "TelegramFSM"

        public val Pipeline: PipelineElement<Pipeline> by PipelineElement

        public val Flows: PipelineElement<List<TelegramFlow>> by PipelineElement

        override fun apply(context: MutablePipelineContext) {

        }
    }
}

public inline fun buildTelegramFSM(
    from: TelegramFSM? = null,
    block: TelegramFSM.Builder.() -> Unit = {}
): TelegramFSM {
    val builder = when (from) {
        null -> TelegramFSM.Builder()
        else -> TelegramFSM.Builder(from.context)
    }
    builder.block()
    return builder.build()
}

public fun MutablePipelineContext.set(
    element: PipelineElement<TelegramFSM>,
    block: TelegramFSM.Builder.() -> Unit
  ): TelegramFSM {
    return buildTelegramFSM(context[element], block)
}

