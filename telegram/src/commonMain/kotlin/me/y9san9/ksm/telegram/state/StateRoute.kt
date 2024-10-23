package me.y9san9.ksm.telegram.state

import me.y9san9.ksm.telegram.state.base.StateBase
import me.y9san9.pipeline.context.require
import kotlin.reflect.KProperty

public data class StateRoute(public val name: String) {
    public companion object {
        public operator fun provideDelegate(
            thisRef: Any?,
            property: KProperty<*>
        ): Lazy<StateRoute> {
            return lazy { StateRoute(property.name) }
        }
    }
}

public val State.route: StateRoute
    get() = context.require(StateBase.Config.Route)
