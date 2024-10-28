package me.y9san9.ksm.telegram.group

import me.y9san9.ksm.telegram.group.base.UpdateGroupBase
import me.y9san9.ksm.telegram.routing.UpdateStateList
import me.y9san9.pipeline.context.require

public val UpdateStateGroup.stateList: UpdateStateList
    get() = context.require(UpdateGroupBase.Config.StateList)
