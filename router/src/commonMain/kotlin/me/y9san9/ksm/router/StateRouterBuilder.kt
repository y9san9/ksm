package me.y9san9.ksm.router

import me.y9san9.ksm.router.plugin.installStateRouterBase
import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.PipelineBuilder
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.buildPipeline
import me.y9san9.pipeline.context.*

@PipelineDsl
public interface StateRouterBuilder {
    public val context: MutablePipelineContext

    public companion object {
        public fun of(): StateRouterBuilder {
            return of(PipelineContext.Empty).apply {
                installStateRouterBase()
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
