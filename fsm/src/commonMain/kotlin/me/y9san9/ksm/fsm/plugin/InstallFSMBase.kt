package me.y9san9.ksm.fsm.plugin

import me.y9san9.ksm.fsm.FSMBuilder
import me.y9san9.pipeline.plugin.register

public fun FSMBuilder.installFSMBase() {
    context.register(FSMBasePlugin)
    FSMBasePlugin.apply(builder = this)
}
