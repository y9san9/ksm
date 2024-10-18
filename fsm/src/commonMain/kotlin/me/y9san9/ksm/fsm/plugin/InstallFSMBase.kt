package me.y9san9.ksm.fsm.plugin

import me.y9san9.ksm.fsm.FSMBuilder
import me.y9san9.pipeline.plugin.install

public fun FSMBuilder.installFSMBase() {
    context.install(FSMBasePlugin)
    FSMBasePlugin.apply(builder = this)
}
