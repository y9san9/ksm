package me.y9san9.ksm.telegram.json

import kotlinx.serialization.json.encodeToJsonElement
import me.y9san9.ksm.telegram.json.base.JsonPlugin
import me.y9san9.ksm.telegram.state.StateName
import me.y9san9.ksm.telegram.state.routing.StateRouter
import me.y9san9.ksm.telegram.state.routing.goto
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.require

@PipelineDsl
public suspend inline fun <reified T> StateRouter.goto(
    state: StateName,
    data: T?,
    transition: Boolean = true
): Nothing {
    val json = context.require(JsonPlugin.Subject.Json) { "Please setup `json` in buildTelegramFSM" }
    val jsonElement = json.encodeToJsonElement(data)
    goto(state, jsonElement.toStateData(), transition)
}
