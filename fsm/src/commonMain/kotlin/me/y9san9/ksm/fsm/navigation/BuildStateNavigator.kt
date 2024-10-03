package me.y9san9.ksm.fsm.navigation

public inline fun buildStateNavigator(
    base: StateNavigator? = null,
    block: StateNavigatorBuilder.() -> Unit = {}
): StateNavigator {
    val builder = StateNavigatorBuilder.of(base?.context)
    builder.apply(block)
    return builder.build()
}

public inline fun StateNavigator.build(block: StateNavigatorBuilder.() -> Unit): StateNavigator {
    return buildStateNavigator(base = this, block = block)
}
