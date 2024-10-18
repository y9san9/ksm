package me.y9san9.ksm.router

import me.y9san9.ksm.router.plugin.StateRouterBase
import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.proceed

public interface StateRouter {
    public val context: PipelineContext

    public suspend fun proceed(subject: PipelineContext): PipelineContext {
        val pipeline = context.require(StateRouterBase.Config.Pipeline)
        return pipeline.proceed(context, subject)
    }

    public companion object {
        public fun of(context: PipelineContext): StateRouter {
            require(context)
            return object : StateRouter {
                override val context = context
                override fun toString(): String = "Router(context=$context)"
            }
        }

        public fun require(context: PipelineContext) {
            context.require(StateRouterBase.Config.Pipeline)
        }
    }
}
