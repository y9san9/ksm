package me.y9san9.ksm.telegram.state

import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.plugin.PipelinePlugin
import me.y9san9.pipeline.plugin.install

public data class State(public val context: PipelineContext) {
    public val name: StateName get() = context.require(Name)
    public val transition: StateTransition? get() = context[Transition]
    public val handler: StateHandler? get() = context[Handler]

    @PipelineDsl
    public class Builder(context: PipelineContext) {
        public val context: MutablePipelineContext = mutablePipelineContextOf(context)

        public constructor() : this(PipelineContext.Empty) {
            context.install(State)
        }

        @PipelineDsl
        public var name: StateName?
            get() = context[Name]
            set(value) { context[Name] = value }

        @PipelineDsl
        public var handler: StateHandler?
            get() = context[Handler]
            set(value) { context[Handler] = value }

        @PipelineDsl
        public var transition: StateTransition?
            get() = context[Transition]
            set(value) { context[Transition] = value }

        public fun build(): State {
            return State(context.toPipelineContext())
        }
    }

    public companion object Plugin : PipelinePlugin {
        override val name: String = "State"

        public val Name: PipelineElement<StateName> by PipelineElement
        public val Transition: PipelineElement<StateTransition> by PipelineElement
        public val Handler: PipelineElement<StateHandler> by PipelineElement

        override fun apply(context: MutablePipelineContext) {}
    }
}
