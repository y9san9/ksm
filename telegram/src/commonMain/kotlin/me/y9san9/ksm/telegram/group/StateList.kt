package me.y9san9.ksm.telegram.group

import me.y9san9.ksm.telegram.group.base.StateGroupBase
import me.y9san9.ksm.telegram.routing.StateList
import me.y9san9.pipeline.context.require

public val StateGroup.stateList: StateList
    get() = context.require(StateGroupBase.Config.StateList)
