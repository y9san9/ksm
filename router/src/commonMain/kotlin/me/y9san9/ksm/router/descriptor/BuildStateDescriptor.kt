package me.y9san9.ksm.router.descriptor

public inline fun buildStateDescriptor(
    base: StateDescriptor? = null,
    block: StateDescriptorBuilder.() -> Unit = {}
): StateDescriptor {
    val builder = StateDescriptorBuilder.of(base?.context)
    builder.apply(block)
    return builder.build()
}

public inline fun StateDescriptor.build(block: StateDescriptorBuilder.() -> Unit): StateDescriptor {
    return buildStateDescriptor(base = this, block = block)
}
