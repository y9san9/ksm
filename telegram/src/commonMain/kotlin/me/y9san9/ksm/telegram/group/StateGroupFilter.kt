package me.y9san9.ksm.telegram.group

import dev.inmo.tgbotapi.types.update.abstracts.Update
import me.y9san9.ksm.telegram.group.base.UpdateGroupBase

public fun interface StateGroupFilter {
    public fun filter(flow: Update): Boolean

    public object Default : StateGroupFilter {
        override fun filter(flow: Update): Boolean = true
    }
}

public val UpdateStateGroup.filter: StateGroupFilter
    get() = context[UpdateGroupBase.Config.Filter] ?: StateGroupFilter.Default
