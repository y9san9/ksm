package me.y9san9.ksm.telegram.json.base

import kotlinx.serialization.json.Json
import me.y9san9.pipeline.context.PipelineElement

public data object JsonPlugin {
    public val Json: PipelineElement<Json> by PipelineElement
}
