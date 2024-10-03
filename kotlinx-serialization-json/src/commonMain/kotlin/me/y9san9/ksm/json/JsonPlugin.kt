package me.y9san9.ksm.json

import me.y9san9.ksm.restore.RestorePlugin
import me.y9san9.pipeline.PipelineBuilder
import me.y9san9.pipeline.builder.with
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.insertPhaseAfter
import me.y9san9.pipeline._PipelineRunnable
import me.y9san9.pipeline.plugin.dependsOn
import me.y9san9.pipeline.plugin.PipelinePlugin

public object JsonPlugin : PipelinePlugin {
    public fun apply(builder: PipelineBuilder) {
        with(builder) {
            context.dependsOn(RestorePlugin)

            context.withPipeline(RestorePlugin.RestorePipeline) {
                insertPhaseAfter(_PipelineRunnable.Start.name, JsonDecodePhase)
            }
            context.withPipeline(RestorePlugin.SavePipeline) {
                insertPhaseBefore(_PipelineRunnable.Finish.name, JsonEncodePhase)
            }

            context.with(Json, kotlinx.serialization.json.Json)
        }
    }

    public object Json : PipelineElement<kotlinx.serialization.json.Json>
}
