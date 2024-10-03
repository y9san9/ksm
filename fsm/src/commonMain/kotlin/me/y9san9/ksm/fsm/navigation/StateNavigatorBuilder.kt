package me.y9san9.ksm.fsm.navigation

import me.y9san9.ksm.fsm.navigation.plugin.installStateNavigatorBase
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.mutablePipelineContextOf
import me.y9san9.pipeline.context.toPipelineContext

@PipelineDsl
public interface StateNavigatorBuilder {
    public val context: MutablePipelineContext

    public companion object {
        public fun of(): StateNavigatorBuilder {
            return of(PipelineContext.Empty).apply {
                installStateNavigatorBase()
            }
        }

        public fun of(context: PipelineContext? = null): StateNavigatorBuilder {
            context ?: return of()

            return object : StateNavigatorBuilder {
                override val context = mutablePipelineContextOf(context)
                override fun toString() = "StateNavigatorBuilder(context=${this.context})"
            }
        }
    }
}

public fun StateNavigatorBuilder.build(): StateNavigator {
    return StateNavigator.of(context.toPipelineContext())
}
