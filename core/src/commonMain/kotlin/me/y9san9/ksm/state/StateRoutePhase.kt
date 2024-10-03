package me.y9san9.ksm.state

import me.y9san9.ksm.route.RoutePlugin
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.plus
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline._PipelineRunnable

public object StateRoutePhase : _PipelineRunnable {
    override val name: String = "StateRoute"

    override suspend fun proceed(context: PipelineContext): PipelineContext {
        val descriptor = context.require(StatePlugin.StateDescriptor)
        val routes = context.require(RoutePlugin.List)

        for (route in routes.list) {
            val routeName = route.require(StatePlugin.StateName)
            if (routeName.string == descriptor.name.string) {
                return context + route
            }
        }

        error("Cannot find state named '${descriptor.name.string}'")
    }
}
