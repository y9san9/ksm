import me.y9san9.ksm.router.*
import me.y9san9.ksm.router.descriptor.buildStateDescriptor
import me.y9san9.ksm.router.descriptor.name
import me.y9san9.ksm.router.docs.docs
import me.y9san9.ksm.routing.buildStateList
import me.y9san9.ksm.routing.state
import me.y9san9.ksm.state.handle
import me.y9san9.ksm.state.name
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.docs.*
import me.y9san9.pipeline.docs.plugin.PipelineDocsPlugin.name

suspend fun main() {
    val router = buildStateRouter {

    }

    val states = buildStateList {
        initial = "Main"

        state {
            name = "Main"

            handle {
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
