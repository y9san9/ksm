package me.y9san9.ksm.telegram.state

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
