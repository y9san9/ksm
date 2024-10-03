package me.y9san9.ksm.ktgbotapi.state

import me.y9san9.ksm.events.EventsPlugin
import me.y9san9.ksm.ktgbotapi.BotControllerBuilder
import me.y9san9.ksm.route.PipelineRouteList
import me.y9san9.ksm.route.RoutePlugin
import me.y9san9.ksm.state.StatePlugin
import me.y9san9.pipeline.builder.pipeline
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require

public class Routing {
    public val routes: MutableList<PipelineContext> = mutableListOf()
}

public inline fun BotControllerBuilder.routing(
    initialState: String,
    block: Routing.() -> Unit
) {
    val routing = Routing()
    block(routing)

    val initial = routing.routes.firstOrNull { route ->
        route.require(StatePlugin.StateName).string == initialState
    } ?: error("Cannot find state `$initialState` that you set as initial")

    val list = PipelineRouteList(initial, routing.routes)

    builder.pipeline(EventsPlugin.Pipeline) {
        with(RoutePlugin.List, list)
    }
}
