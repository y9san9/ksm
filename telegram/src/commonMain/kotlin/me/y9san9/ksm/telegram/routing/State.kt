package me.y9san9.ksm.telegram.routing

import me.y9san9.ksm.telegram.state.State
import me.y9san9.ksm.telegram.state.buildState

public inline fun StateRouting.state(block: State.Builder.() -> Unit) {
    states += buildState(block = block)
}
