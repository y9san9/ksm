import me.y9san9.ksm.router.*
import me.y9san9.ksm.router.descriptor.buildStateDescriptor
import me.y9san9.ksm.router.descriptor.name
import me.y9san9.ksm.router.docs.docs
import me.y9san9.ksm.routing.buildStateList
import me.y9san9.ksm.routing.state
import me.y9san9.ksm.state.action
import me.y9san9.ksm.state.name
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.docs.*
import me.y9san9.pipeline.docs.plugin.PipelineDocsPlugin
import me.y9san9.pipeline.docs.plugin.PipelineDocsPlugin.name
import kotlin.time.measureTime

suspend fun main() {
    val router = buildStateRouter {

    }

    val states = buildStateList {
        initial = "Main"

        state {
            name = "Main"

            action {
                println("TEST")
            }
        }
    }

    println(router.docs().prettyString())

    val descriptor = buildStateDescriptor {
        name = "Main"
        context[StateList] = states
    }

    router.route(descriptor)
}
