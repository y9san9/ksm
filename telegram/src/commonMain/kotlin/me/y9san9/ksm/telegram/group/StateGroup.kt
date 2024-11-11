package me.y9san9.ksm.telegram.group

import me.y9san9.aqueue.AQueue
import me.y9san9.ksm.telegram.handler.UpdateFilter
import me.y9san9.ksm.telegram.handler.UpdateKey
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.plugin.PipelinePlugin

public interface StateGroup {
    public val context: PipelineContext

    public val aqueue: AQueue get() = context.require(AQueue)

    @PipelineDsl
    public interface Builder {
        public val context: MutablePipelineContext

        public var aqueue: AQueue?
            get() = context[AQueue]
            set(value) { context[AQueue] = value }
    }

    public companion object Plugin : PipelinePlugin {
        override val name: String = "StateGroup"

        public val Key: PipelineElement<UpdateKey> by PipelineElement
        public val Filter: PipelineElement<UpdateFilter> by PipelineElement
        public val AQueue: PipelineElement<AQueue> by PipelineElement

        override fun apply(context: MutablePipelineContext) {
            context[AQueue] = AQueue()
            context[Key] = UpdateKey { null }
            context[Filter] = UpdateFilter { true }
        }
    }
}

