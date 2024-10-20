package me.y9san9.ksm.telegram.json

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import me.y9san9.ksm.telegram.state.StateHandler
import me.y9san9.ksm.telegram.state.goto

public suspend inline fun <reified T> StateHandler.Scope.goto(
    id: String,
    data: T? = null,
    json: Json = Json
): Nothing {
    val jsonElement = json.encodeToJsonElement(data)
    goto(id, jsonElement.toStateData())
}
