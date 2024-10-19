import kotlinx.coroutines.coroutineScope
import me.y9san9.ksm.state.handle
import me.y9san9.ksm.state.buildState
import me.y9san9.ksm.state.docs.docs
import me.y9san9.ksm.state.finish
import me.y9san9.ksm.state.name
import me.y9san9.ksm.state.runner.buildStateRunner
import me.y9san9.pipeline.docs.*

suspend fun main() {
    val state = buildState {
        name = "Main"

        handle {
            println("TEST!")
            coroutineScope {
                finish()
            }
            println("TEST!")
        }
    }

    val runner = buildStateRunner {

    }

    println(runner.docs().prettyString())
    runner.run(state)
//    pipeline.proceed()
}
