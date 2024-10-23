package me.y9san9.ksm.telegram.group

import me.y9san9.ksm.telegram.TelegramFSM
import me.y9san9.ksm.telegram.base.TelegramFSMBase
import me.y9san9.ksm.telegram.group.base.StateGroupBase
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.plugin.install
import me.y9san9.pipeline.setSubject
import me.y9san9.pipeline.subject

public class StateGroup(public val context: PipelineContext) {
    @PipelineDsl
    public class Builder(context: PipelineContext) {
        public val context: MutablePipelineContext = mutablePipelineContextOf(context)

        public constructor() : this(PipelineContext.Empty) {
            context.install(StateGroupBase)
        }

        public fun build(): StateGroup {
            return StateGroup(context.toPipelineContext())
        }
    }
}

public inline fun buildStateGroup(
    from: StateGroup? = null,
    unnamed: Boolean = false,
    block: StateGroup.Builder.() -> Unit
): StateGroup {
    val builder = when (from) {
        null -> StateGroup.Builder()
        else -> StateGroup.Builder(from.context)
    }
    builder.block()
    if (!unnamed) {
        builder.context.require(StateGroupBase.Config.Name) { "Please provide 'name' for StateGroup" }
    }
    return builder.build()
}

@PipelineDsl
public inline fun TelegramFSM.Builder.stateGroup(block: StateGroup.Builder.() -> Unit) {
    val group = buildStateGroup(unnamed = true, block = block)
    val groups = context.subject.require(TelegramFSMBase.Subject.StateGroups)
    context.setSubject(TelegramFSMBase.Subject.StateGroups, groups + group)
}
