import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.mutablePipelineContextOf
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.plugin.PipelinePlugin

class Car(val context: PipelineContext) {

    class Builder {
        val context: MutablePipelineContext = mutablePipelineContextOf()
    }

    companion object Plugin : PipelinePlugin {
        override val name: String = "Car"

        val Engine: PipelineElement<PipelineContext> by PipelineElement

        override fun apply(context: MutablePipelineContext) {}
    }
}

class Engine(val context: PipelineContext) {

    class Builder {
        val context: MutablePipelineContext = mutablePipelineContextOf()
    }

    companion object Plugin : PipelinePlugin {
        override val name: String = "Engine"

        val Name: PipelineElement<String> by PipelineElement

        override fun apply(context: MutablePipelineContext) {}
    }
}


fun main() {
    val carContext = mutablePipelineContextOf()
    val engineContext = carContext[Car.Engine]
    engineContext[Engine.Name] = "Super Duper Engine"
    println(carContext)
}
