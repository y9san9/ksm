package me.y9san9.ksm.router.plugin

import me.y9san9.ksm.router.StateRouterBuilder
import me.y9san9.pipeline.plugin.register

public fun StateRouterBuilder.installStateRouterBase() {
    context.register(StateRouterBasePlugin)
    StateRouterBasePlugin.apply(builder = this)
}
