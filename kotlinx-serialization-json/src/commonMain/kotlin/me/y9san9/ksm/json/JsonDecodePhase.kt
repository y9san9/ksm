package me.y9san9.ksm.json

import kotlinx.serialization.json.JsonElement
import me.y9san9.ksm.state.StateData
import me.y9san9.ksm.restore.RestorePlugin
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.with
import me.y9san9.pipeline._PipelineRunnable

public object JsonDecodePhase : _PipelineRunnable {
    override val name: String = "JsonDecode"

    override suspend fun proceed(context: PipelineContext): PipelineContext {
        val string = context.require(RestorePlugin.String)
        val json = context.require(JsonPlugin.Json)
        val decoded: JsonElement = json.decodeFromString(string)
        val stateData = decoded.toRouteData() as StateData.Map
        return context.with(RestorePlugin.Data, stateData)
    }
}
