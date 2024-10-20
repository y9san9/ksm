import me.y9san9.pipeline.buildPipeline
import me.y9san9.pipeline.insertPhaseFirst
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable
import me.y9san9.pipeline.proceed

suspend fun main() {
    val pipeline = buildPipeline {
        insertPhaseFirst {
            name = "TestPipelinePhase"

            runnable {
                println("TEST")
            }
        }
    }

    pipeline.proceed()
}
