package me.y9san9.ksm.fsm

public inline fun buildFSM(
    base: FSM? = null,
    block: FSMBuilder.() -> Unit
): FSM {
    val builder = FSMBuilder.of(base?.context)
    builder.apply(block)
    return builder.build()
}
