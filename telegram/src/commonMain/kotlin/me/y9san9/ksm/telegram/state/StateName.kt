package me.y9san9.ksm.telegram.state

import me.y9san9.ksm.telegram.state.base.StateBase
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set

public val State.name: String
    get() = context.require(StateBase.Config.Name)

@PipelineDsl
public var State.Builder.name: String
    get() = context.require(StateBase.Config.Name)
    set(value) { context[StateBase.Config.Name] = value }
