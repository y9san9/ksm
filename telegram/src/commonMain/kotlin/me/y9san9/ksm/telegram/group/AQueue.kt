package me.y9san9.ksm.telegram.group

import me.y9san9.aqueue.AQueue
import me.y9san9.ksm.telegram.group.base.UpdateGroupBase
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set

public val UpdateGroup.aqueue: AQueue
    get() = context.require(UpdateGroupBase.Config.AQueue)

public var UpdateGroup.Builder.aqueue: AQueue
    get() = context.require(UpdateGroupBase.Config.AQueue)
    set(value) { context[UpdateGroupBase.Config.AQueue] = value }
