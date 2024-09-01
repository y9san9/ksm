package me.y9san9.ksm.json

import me.y9san9.ksm.restore.RestorePlugin
import me.y9san9.pipeline.builder.PipelineBuilder
import me.y9san9.pipeline.builder.with
import me.y9san9.pipeline.builder.withPipeline
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.dependsOn
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.plugin.PipelinePlugin

public object JsonPlugin : PipelinePlugin {
    override fun apply(builder: PipelineBuilder) {
        with(builder) {
            context.dependsOn(RestorePlugin)

            withPipeline(RestorePlugin.RestorePipeline) {
                insertPhaseAfter(PipelinePhase.Start.name, JsonDecodePhase)
            }
            withPipeline(RestorePlugin.SavePipeline) {
                insertPhaseBefore(PipelinePhase.Finish.name, JsonEncodePhase)
            }

            with(Json, kotlinx.serialization.json.Json)
        }
    }

    public object Json : PipelineElement<kotlinx.serialization.json.Json>
}
