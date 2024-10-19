package me.y9san9.ksm.router

import me.y9san9.ksm.router.plugin.StateRouterBase
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.plugin.install

@PipelineDsl
public interface StateRouterBuilder {
    public val context: MutablePipelineContext

    public companion object {
        public fun of(): StateRouterBuilder {
            return of(PipelineContext.Empty).apply {
                context.install(StateRouterBase)
            }
        }

        public fun of(context: PipelineContext? = null): StateRouterBuilder {
            context ?: return of()

            return object : StateRouterBuilder {
                override val context = mutablePipelineContextOf(context)
                override fun toString() = "StateRouterBuilder(context=$context)"
            }
        }
    }
}

public fun StateRouterBuilder.build(): StateRouter {
    return StateRouter.of(context.toPipelineContext())
}
