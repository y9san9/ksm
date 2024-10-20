package me.y9san9.ksm.telegram.json

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.JsonUnquotedLiteral
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.doubleOrNull
import kotlinx.serialization.json.longOrNull
import me.y9san9.ksm.telegram.state.data.StateData

public fun JsonElement.toStateData(): StateData = when (this) {
    is JsonArray -> StateData.List(map(JsonElement::toStateData))
    is JsonObject -> StateData.Map(map { (key, value) -> key to value.toStateData() }.toMap())
    JsonNull -> StateData.Null
    is JsonPrimitive -> when {
        longOrNull != null -> StateData.Int(content)
        doubleOrNull != null -> StateData.Float(content)
        booleanOrNull != null -> StateData.Boolean(boolean)
        else -> StateData.String(content)
    }
}

@OptIn(ExperimentalSerializationApi::class)
public fun StateData.toJsonElement(): JsonElement = when (this) {
    StateData.Null -> JsonNull
    is StateData.Boolean -> JsonPrimitive(boolean)
    is StateData.Float -> JsonUnquotedLiteral(string)
    is StateData.Int -> JsonUnquotedLiteral(string)
    is StateData.String -> JsonPrimitive(string)
    is StateData.List -> JsonArray(list.map(StateData::toJsonElement))
    is StateData.Map -> JsonObject(map.map { (key, value) -> key to value.toJsonElement() }.toMap())
}