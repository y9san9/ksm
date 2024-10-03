import me.y9san9.pipeline.*
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.docs.*
import me.y9san9.pipeline.docs.contextDescription
import me.y9san9.pipeline.docs.description
import me.y9san9.pipeline.docs.subjectDescription
import me.y9san9.pipeline.docs.name
import me.y9san9.pipeline.docs.docs
import me.y9san9.pipeline.docs.plugin.installPipelineDocs
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable

suspend fun main() {
    val pipeline = buildPipeline {
        installPipelineDocs()

        describe {
            name = "TestPipeline"
            description = "Just testing prototyped API for Pipelines"
        }

        val startPipeline = buildPipeline {
            installPipelineDocs()

            describe {
                name = "Start"
                description = "This phase does nothing to context"
                contextDescription = listOf("Nothing")
                subjectDescription = listOf("Nothing")
            }

            val start = buildPipelinePhase {
                name = "Start"

                describe {
                    description = "This phase does nothing to context"
                    contextDescription = listOf("Nothing")
                    subjectDescription = listOf("Nothing")
                }

                runnable { }
            }

            insertPhaseFirst(start)
        }
//
        insertPhaseFirst {
            name = "TestPipelinePhase"

            describe {
                // For some reason returns PipelineDocsPhases=[PipelineDocs(context=me.y9san9.pipeline.context.PipelineContext$Empty@4c3e4790)],
                //  with empty docs in phase
                from(startPipeline.docs())
            }

            runnable {
                startPipeline.proceed(context, PipelineContext.Empty)
            }
        }
    }

    println("Docs: ")
    println(pipeline.docs(PipelineContext.Empty).prettyString())
//    pipeline.proceed()
}
