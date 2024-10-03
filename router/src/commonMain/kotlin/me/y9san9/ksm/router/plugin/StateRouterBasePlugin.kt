package me.y9san9.ksm.router.plugin

import me.y9san9.ksm.router.StateRouterBuilder
import me.y9san9.ksm.router.StateRouterPipeline
import me.y9san9.pipeline.buildPipeline
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.docs.describe
import me.y9san9.pipeline.docs.description
import me.y9san9.pipeline.docs.name
import me.y9san9.pipeline.docs.plugin.installPipelineDocs
import me.y9san9.pipeline.insertPhaseFirst
import me.y9san9.pipeline.plugin.PipelinePlugin

public object StateRouterBasePlugin : PipelinePlugin {
    override val name: String = "StateRouterBase"

    public fun apply(builder: StateRouterBuilder) {
        with(builder) {
            context[StateRouterPipeline] = buildPipeline {
                installPipelineDocs()

                describe {
                    name = "StateRouterPipeline"
                    description = "Run this pipeline to select state from list of states. Returned subject is PipelineContext of the selected State"
                }

                insertPhaseFirst(RouteByNamePhase)
            }
        }
    }
}
