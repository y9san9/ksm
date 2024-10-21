package me.y9san9.ksm.telegram.routing

import me.y9san9.ksm.telegram.routing.base.StateListBase.Config
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set

@PipelineDsl
public var StateRouting.initial: String
    get() = context.require(Config.Initial)
    set(value) { context[Config.Initial] = value }
