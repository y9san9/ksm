package me.y9san9.ksm.state

import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set

public object StateName : PipelineElement<String>

public val State.name: String get() = context.require(StateName)

public var StateBuilder.name: String
    get() = context.require(StateName)
    set(value) { context[StateName] = value }
