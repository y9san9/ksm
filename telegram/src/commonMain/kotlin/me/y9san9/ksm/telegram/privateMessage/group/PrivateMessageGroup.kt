package me.y9san9.ksm.telegram.privateMessage.group

import me.y9san9.ksm.telegram.group.StateGroup
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.mutablePipelineContextOf
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.plugin.PipelinePlugin
import me.y9san9.pipeline.plugin.install

public class PrivateMessageGroup(override val context: PipelineContext) : StateGroup {
    public val filter: PrivateMessageFilter get() = context.require(Filter)
    public val key: PrivateMessageKey get() = context.require(Key)

    @PipelineDsl
    public class Builder(context: PipelineContext) : StateGroup.Builder {
        override val context: MutablePipelineContext = mutablePipelineContextOf(context)

        public var filter: PrivateMessageFilter
            get() = context.require(Filter)
            set(value) { context[Filter] = value }

        public var key: PrivateMessageKey
            get() = context.require(Key)
            set(value) { context[Key] = value }

        public constructor() : this(PipelineContext.Empty) {
            context.install(PrivateMessageGroup)
        }

        public fun build(): PrivateMessageGroup {
            return PrivateMessageGroup(context.toPipelineContext())
        }
    }

    public companion object Plugin : PipelinePlugin {
        override val name: String = "PrivateMessageGroup"

        public val Filter: PipelineElement<PrivateMessageFilter> by PipelineElement
        public val Key: PipelineElement<PrivateMessageKey> by PipelineElement

        override fun apply(context: MutablePipelineContext) {
            context.install(StateGroup)
        }
    }
}

public inline fun buildPrivateMessageGroup(
    from: PrivateMessageGroup? = null,
    block: PrivateMessageGroup.Builder.() -> Unit = {}
): PrivateMessageGroup {
    val builder = when (from) {
        null -> PrivateMessageGroup.Builder()
        else -> PrivateMessageGroup.Builder(from.context)
    }
    builder.block()
    return builder.build()
}

public fun MutablePipelineContext.set(
    element: PipelineElement<PrivateMessageGroup>,
    block: PrivateMessageGroup.Builder.() -> Unit
): PrivateMessageGroup {
    return buildPrivateMessageGroup(context[element], block)
}
