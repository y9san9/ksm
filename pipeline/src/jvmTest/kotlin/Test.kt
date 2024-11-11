import me.y9san9.pipeline.buildPipeline
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.insertPhaseLast
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.proceed

object Test {
    val Name: PipelineElement<String> by PipelineElement
    val Context: PipelineElement<PipelineContext> by PipelineElement
}

val firstPhase = buildPipelinePhase {
    name = "firstPhase"

    runnable {
        print("Hello ")
        context[Test.Name] = "World!"
    }
}

val secondPhase = buildPipelinePhase {
    name = "firstPhase"

    runnable {
        println(context[Test.Name])
        context[Test.Context] = toPipelineContext()
        println(toPipelineContext())
    }
}

suspend fun main() {
    val pipeline = buildPipeline {
        insertPhaseLast(firstPhase)
        insertPhaseLast(secondPhase)
    }

    pipeline.proceed(PipelineContext.Empty)
}
