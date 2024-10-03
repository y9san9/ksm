package me.y9san9.pipeline.docs.plugin

import me.y9san9.pipeline.*
import me.y9san9.pipeline.docs.docsPipeline
import me.y9san9.pipeline.plugin.PipelinePlugin
import me.y9san9.pipeline.plugin.register

public fun PipelineBuilder.installPipelineDocs() {
    context.register(PipelineDocsPlugin)
    PipelineDocsPlugin.apply(builder = this)
}

public object PipelineDocsPlugin : PipelinePlugin {
    override val name: String = "PipelineDocs"

    public fun apply(builder: PipelineBuilder) {
        with(builder) {
            docsPipeline {
                insertPhaseFirst(DescribePipelinePhase)
            }
        }
    }
}
