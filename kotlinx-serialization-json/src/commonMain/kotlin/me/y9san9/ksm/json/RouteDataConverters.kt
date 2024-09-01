package me.y9san9.ksm.json

import kotlinx.serialization.json.*
import me.y9san9.ksm.state.StateData

public inline fun <reified T> StateData.receive(json: Json = Json): T {
    return json.decodeFromJsonElement(toJsonElement())
}

public inline fun <reified T> stateDataOf(data: T, json: Json = Json): StateData {
    return json.encodeToJsonElement(data).toRouteData()
}

public fun StateData.toJsonElement(): JsonElement {
    return when (this) {
        is StateData.List -> JsonArray(list.map(StateData::toJsonElement))
        is StateData.Map -> JsonObject(map.mapValues { (_, value) -> value.toJsonElement() })
        is StateData.String -> JsonPrimitive(string)
    }
}

public fun JsonElement.toRouteData(): StateData {
    return when (this) {
        is JsonArray -> StateData.List(this.map(JsonElement::toRouteData))
        is JsonObject -> StateData.Map(mapValues { (_, value) -> value.toRouteData() })
        is JsonPrimitive -> when {
            isString -> StateData.String(content)
            this is JsonNull -> TODO()
            else -> TODO()
        }
    }
}
