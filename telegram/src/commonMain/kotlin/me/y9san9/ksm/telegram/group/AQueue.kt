package me.y9san9.ksm.telegram.group

import me.y9san9.aqueue.AQueue
import me.y9san9.ksm.telegram.group.base.UpdateStateGroupBase
import me.y9san9.ksm.telegram.group.base.UpdateStateGroupBase.AQueue
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set

public val UpdateStateGroup.aqueue: AQueue
    get() = context.require(AQueue)
