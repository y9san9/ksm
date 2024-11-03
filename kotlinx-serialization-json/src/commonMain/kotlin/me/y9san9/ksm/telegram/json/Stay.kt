package me.y9san9.ksm.telegram.json

import kotlinx.serialization.json.encodeToJsonElement
import me.y9san9.ksm.telegram.json.base.JsonPlugin
import me.y9san9.ksm.telegram.state.UpdateHandler
import me.y9san9.ksm.telegram.state.routing.StateRouter
import me.y9san9.ksm.telegram.state.routing.stay
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.require

@PipelineDsl
public suspend inline fun <reified T> StateRouter.stay(
    data: T,
    transition: Boolean = false
): Nothing {
    val json = context.require(JsonPlugin.Json) { "Please setup `json` in buildTelegramFSM" }
    val jsonElement = json.encodeToJsonElement(data)
    stay(jsonElement.toStateData(), transition)
}
