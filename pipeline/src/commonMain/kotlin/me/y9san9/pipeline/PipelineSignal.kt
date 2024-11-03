package me.y9san9.pipeline

import me.y9san9.pipeline.context.PipelineElement

public object PipelineSignal {
    /**
     * Signals that current pipeline is completed normally.
     *
     * [Return] signal is not present in the resulting context.
     */
    public val Return: PipelineElement<Unit> by PipelineElement

    /**
     * Signals that current pipeline is completed with error.
     * This signal propagates up from children to its parent pipelines.
     *
     * [Throw] signal is present in the resulting context.
     */
    public val Throw: PipelineElement<Unit> by PipelineElement
}
