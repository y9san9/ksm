package me.y9san9.ksm.telegram.group

import me.y9san9.aqueue.AQueue
import me.y9san9.ksm.telegram.group.base.StateGroupBase
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set

public val StateGroup.aqueue: AQueue
    get() = context.require(StateGroupBase.Config.AQueue)

public var StateGroup.Builder.aqueue: AQueue
    get() = context.require(StateGroupBase.Config.AQueue)
    set(value) { context[StateGroupBase.Config.AQueue] = value }
