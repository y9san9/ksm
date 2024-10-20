package me.y9san9.ksm.telegram.json

import kotlinx.serialization.json.encodeToJsonElement
import me.y9san9.ksm.telegram.json.base.JsonPlugin
import me.y9san9.ksm.telegram.state.StateHandler
import me.y9san9.ksm.telegram.state.goto
import me.y9san9.pipeline.context.require

public suspend inline fun <reified T> StateHandler.Scope.goto(
    data: T,
    transition: Boolean = false
): Nothing {
    val json = context.require(JsonPlugin.Subject.Json) { "Please setup `json` in buildTelegramFSM" }
    val jsonElement = json.encodeToJsonElement(data)
    goto(jsonElement.toStateData(), transition)
}

public suspend inline fun StateHandler.Scope.goto(name: String): Nothing {
    goto(name)
}

public suspend inline fun <reified T> StateHandler.Scope.goto(
    name: String,
    data: T? = null,
    transition: Boolean = true
): Nothing {
    val json = context.require(JsonPlugin.Subject.Json) { "Please setup `json` in buildTelegramFSM" }
    val jsonElement = json.encodeToJsonElement(data)
    goto(name, jsonElement.toStateData(), transition)
}
