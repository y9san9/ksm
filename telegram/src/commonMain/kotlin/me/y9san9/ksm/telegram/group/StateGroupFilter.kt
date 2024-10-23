package me.y9san9.ksm.telegram.group

import dev.inmo.tgbotapi.types.update.abstracts.Update
import me.y9san9.ksm.telegram.group.base.StateGroupBase
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.set

public fun interface StateGroupFilter {
    public fun filter(flow: Update): Boolean

    public object Default : StateGroupFilter {
        override fun filter(flow: Update): Boolean = true
    }
}

public val StateGroup.filter: StateGroupFilter
    get() = context[StateGroupBase.Config.Filter] ?: StateGroupFilter.Default

@PipelineDsl
public fun StateGroup.Builder.filter(block: (Update) -> Boolean) {
    context[StateGroupBase.Config.Filter] = StateGroupFilter(block)
}
