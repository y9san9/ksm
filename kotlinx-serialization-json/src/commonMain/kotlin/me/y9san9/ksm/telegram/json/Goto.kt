package me.y9san9.ksm.telegram.json

import kotlinx.serialization.json.encodeToJsonElement
import me.y9san9.ksm.telegram.json.base.JsonPlugin
import me.y9san9.ksm.telegram.state.StateHandler
import me.y9san9.ksm.telegram.state.goto
import me.y9san9.pipeline.context.require

public suspend inline fun <reified T> StateHandler.Scope.goto(data: T): Nothing {
    val json = context.require(JsonPlugin.Subject.Json) { "Please setup `json` in buildTelegramFSM" }
    val jsonElement = json.encodeToJsonElement(data)
    goto(jsonElement.toStateData())
}

public suspend inline fun <reified T> StateHandler.Scope.goto(
    id: String,
    data: T? = null
): Nothing {
    val json = context.require(JsonPlugin.Subject.Json) { "Please setup `json` in buildTelegramFSM" }
    val jsonElement = json.encodeToJsonElement(data)
    goto(id, jsonElement.toStateData())
}
