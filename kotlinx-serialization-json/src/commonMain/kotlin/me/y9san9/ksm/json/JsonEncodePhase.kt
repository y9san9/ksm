package me.y9san9.ksm.json

import kotlinx.serialization.encodeToString
import me.y9san9.ksm.restore.RestorePlugin
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.with
import me.y9san9.pipeline.phase.PipelinePhase

public object JsonEncodePhase : PipelinePhase {
    override val name: String = "JsonEncode"

    override suspend fun proceed(context: PipelineContext): PipelineContext {
        val data = context.require(RestorePlugin.Data)
        val json = context.require(JsonPlugin.Json)
        val encoded: String = json.encodeToString(data.toJsonElement())
        return context.with(RestorePlugin.String, encoded)
    }
}
