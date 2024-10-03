package me.y9san9.pipeline.docs.plugin

import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.context.build
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.docs.DescribeProvider
import me.y9san9.pipeline.docs.PipelineDocs
import me.y9san9.pipeline.docs.DocsName
import me.y9san9.pipeline.docs.DocsPhaseList
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable
import me.y9san9.pipeline.phases
import me.y9san9.pipeline.subject.subject

public val DescribePipelinePhase: PipelinePhase = buildPipelinePhase {
    name = "DescribePipeline"

    runnable {
        val pipeline = Pipeline.of(context)

        subject {
            this[DocsPhaseList] = pipeline.phases.map { phase ->
                val provider = phase.context.require(DescribeProvider) { "Phase '${phase.name}' is not documented" }
                val describeContext = pipeline.context.build {
                    subject {
                        this[DocsName] = phase.name
                    }
                }
                val docsContext = provider.describe(describeContext)
                try {
                    PipelineDocs.of(docsContext.subject)
                } catch (e: IllegalStateException) {
                    throw IllegalStateException("Cannot create docs for phase ${phase.name}", e)
                }
            }
        }

        context = context.require(DescribeProvider).describe(context.context)
    }
}
