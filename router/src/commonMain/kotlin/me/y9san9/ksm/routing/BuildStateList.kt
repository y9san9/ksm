package me.y9san9.ksm.routing

import me.y9san9.ksm.router.StateList

public inline fun buildStateList(
    base: StateList? = null,
    block: StateRouting.() -> Unit
): StateList {
    val routing = StateRouting.of(base)
    routing.apply(block)
    return routing.build()
}

public inline fun StateList?.build(block: StateRouting.() -> Unit): StateList {
    return buildStateList(base = this, block = block)
}
