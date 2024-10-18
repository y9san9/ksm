package me.y9san9.ksm.router

public inline fun buildStateRouter(
    base: StateRouter? = null,
    block: StateRouterBuilder.() -> Unit = {}
): StateRouter {
    val builder = StateRouterBuilder.of(base?.context)
    builder.apply(block)
    return builder.build()
}

public inline fun StateRouter?.build(block: StateRouterBuilder.() -> Unit): StateRouter {
    return buildStateRouter(base = this, block = block)
}
