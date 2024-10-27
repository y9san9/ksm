package me.y9san9.ksm.telegram.state

import me.y9san9.ksm.telegram.state.base.UpdateStateBase
import me.y9san9.pipeline.context.require
import kotlin.reflect.KProperty

public data class StateName(public val string: String) {
    public companion object {
        public operator fun provideDelegate(
            thisRef: Any?,
            property: KProperty<*>
        ): Lazy<StateName> {
            return lazy { StateName(property.name) }
        }
    }
}

public val UpdateState.name: StateName
    get() = context.require(UpdateStateBase.Config.Route)
