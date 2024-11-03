package me.y9san9.ksm.telegram.group

import dev.inmo.tgbotapi.types.update.abstracts.Update
import me.y9san9.ksm.telegram.group.base.UpdateStateGroupBase
import me.y9san9.ksm.telegram.group.base.UpdateStateGroupBase.Filter

public fun interface UpdateFilter {
    public fun filter(flow: Update): Boolean

    public object Default : UpdateFilter {
        override fun filter(flow: Update): Boolean = true
    }
}

public val UpdateStateGroup.filter: UpdateFilter
    get() = context[Filter] ?: UpdateFilter.Default
