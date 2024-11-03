package me.y9san9.pipeline.context

import kotlin.reflect.KProperty

public interface PipelineElement<@Suppress("unused") T : Any> {
    public val name: String

    public class Delegate(
        private val thisRef: Any?,
        override val name: String
    ) : PipelineElement<Nothing> {
        public operator fun <T : Any> getValue(thisRef: Any?, property: KProperty<*>): PipelineElement<T> {
            @Suppress("UNCHECKED_CAST")
            return this as PipelineElement<T>
        }

        override fun toString(): String {
            return "$name in $thisRef"
        }
    }

    public companion object {
        public operator fun provideDelegate(
            thisRef: Any?,
            property: KProperty<*>
        ): Delegate {
            return Delegate(thisRef, property.name)
        }
    }
}
