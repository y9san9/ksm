package me.y9san9.ksm.telegram.json

import kotlinx.serialization.json.decodeFromJsonElement
import me.y9san9.ksm.telegram.json.base.JsonPlugin
import me.y9san9.ksm.telegram.state.UpdateHandler
import me.y9san9.ksm.telegram.update.state.data
import me.y9san9.pipeline.context.require

public inline fun <reified T> UpdateHandler.Scope.receive(): T {
    val json = context.require(JsonPlugin.Subject.Json) { "Please setup `json` in buildTelegramFSM" }
    return json.decodeFromJsonElement(data.toJsonElement())
}
