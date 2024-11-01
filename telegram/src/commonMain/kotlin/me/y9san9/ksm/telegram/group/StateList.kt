package me.y9san9.ksm.telegram.group

import me.y9san9.ksm.telegram.group.base.UpdateStateGroupBase
import me.y9san9.ksm.telegram.state.routing.UpdateStateList
import me.y9san9.pipeline.context.require

public val UpdateStateGroup.stateList: UpdateStateList
    get() = context.require(UpdateStateGroupBase.Config.StateList)
