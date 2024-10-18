package me.y9san9.ksm.fsm.navigation

import me.y9san9.ksm.fsm.FSM
import me.y9san9.ksm.fsm.FSMNavigator
import me.y9san9.ksm.fsm.looper.Looper
import me.y9san9.ksm.router.StateDescriptor
import me.y9san9.ksm.router.descriptor.StateDescriptorBuilder
import me.y9san9.ksm.router.descriptor.build
import me.y9san9.ksm.router.descriptor.buildStateDescriptor
import me.y9san9.ksm.state.StateScope
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.subject.subject

public inline fun StateScope.navigate(block: StateDescriptorBuilder.() -> Unit) {
    navigate(buildStateDescriptor(block = block))
}

public fun StateScope.navigate(descriptor: StateDescriptor) {
    val fsm = context.subject.require(FSM)
    val modified = descriptor.build {
        context[FSM] = fsm
    }
    fsm.context.require(Looper).post {
        fsm.context.require(FSMNavigator).navigate(modified)
    }
}
