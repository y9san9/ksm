package me.y9san9.ksm.telegram.state

public fun interface UpdateTransition {
    public suspend fun run(scope: UpdateHandler.Scope)
}
