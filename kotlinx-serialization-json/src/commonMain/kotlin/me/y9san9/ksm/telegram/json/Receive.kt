package me.y9san9.ksm.telegram.json

import kotlinx.serialization.json.decodeFromJsonElement
import me.y9san9.ksm.telegram.json.base.JsonPlugin
import me.y9san9.ksm.telegram.state.data.data
import me.y9san9.ksm.telegram.state.routing.StateRouter
import me.y9san9.pipeline.context.require

public inline fun <reified T> StateRouter.receive(): T {
    val json = context.require(JsonPlugin.Json) { "Please setup `json` in buildTelegramFSM" }
    return json.decodeFromJsonElement(data.toJsonElement())
}
