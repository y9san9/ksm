package me.y9san9.ksm.telegram.json.base

import me.y9san9.pipeline.context.PipelineElement

public object JsonPlugin {
    public object Subject {
        public object Json : PipelineElement<kotlinx.serialization.json.Json>
    }
}
