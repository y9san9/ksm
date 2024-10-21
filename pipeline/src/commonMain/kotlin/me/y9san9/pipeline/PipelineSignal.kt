package me.y9san9.pipeline

import me.y9san9.pipeline.context.PipelineElement

public object PipelineSignal {
    /**
     * Signals that current pipeline is completed normally.
     *
     * [Return] signal is not present in the resulting context.
     */
    public object Return : PipelineElement<Return>

    /**
     * Signals that the next state must be [target].
     *
     * States after current state and before target states are still executed
     */
    public class Goto(public val target: String) {
        public companion object : PipelineElement<Goto>
    }

    /**
     * Signals that current pipeline is completed with error.
     * This signal propagates up from children to its parent pipelines.
     *
     * [Throw] signal is present in the resulting context.
     */
    public object Throw : PipelineElement<Throw>
}
