package me.y9san9.ksm.state.runner

public inline fun buildStateRunner(
    base: StateRunner? = null,
    block: StateRunnerBuilder.() -> Unit = {}
): StateRunner {
    val builder = StateRunnerBuilder.of(base?.context)
    builder.apply(block)
    return builder.build()
}

public fun StateRunner.build(block: StateRunnerBuilder.() -> Unit): StateRunner {
    return buildStateRunner(base = this, block = block)
}
