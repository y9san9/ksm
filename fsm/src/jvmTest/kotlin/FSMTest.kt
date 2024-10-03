import me.y9san9.ksm.fsm.buildFSM
import me.y9san9.ksm.fsm.docs.docs
import me.y9san9.ksm.fsm.navigation.navigate
import me.y9san9.ksm.fsm.routing
import me.y9san9.ksm.fsm.run
import me.y9san9.ksm.router.descriptor.name
import me.y9san9.ksm.routing.state
import me.y9san9.ksm.state.action
import me.y9san9.ksm.state.name
import me.y9san9.pipeline.docs.prettyString

suspend fun main() {
    val fsm = buildFSM {
        routing {
            initial = "Test"

            state {
                name = "Test"

                var times = 0

                action {
                    println("Executing TEST ${++times}")
                    navigate { name = "Test" }
                }
            }
        }
    }

    println(fsm.docs().prettyString())
//    fsm.run()
}
