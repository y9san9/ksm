package me.y9san9.ksm.state

import me.y9san9.ksm.state.plugin.StateBase
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set

public val State.name: String get() = context.require(StateBase.Config.Name)

public var State.Builder.name: String
    get() = context.require(StateBase.Config.Name)
    set(value) { context[StateBase.Config.Name] = value }
