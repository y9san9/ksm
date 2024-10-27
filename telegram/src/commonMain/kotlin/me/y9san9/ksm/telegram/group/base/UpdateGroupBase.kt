package me.y9san9.ksm.telegram.group.base

import me.y9san9.aqueue.AQueue
import me.y9san9.ksm.telegram.group.StateGroupFilter
import me.y9san9.ksm.telegram.group.StateGroupKey
import me.y9san9.ksm.telegram.group.TelegramStorage
import me.y9san9.ksm.telegram.routing.UpdateStateList
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.plugin.PipelinePlugin

public object UpdateGroupBase : PipelinePlugin {
    override val name: String = "StateGroupBase"

    override fun apply(context: MutablePipelineContext) {
        context[Config.AQueue] = AQueue()
    }

    public object Config {
        public object Name : PipelineElement<String>
        public object Key : PipelineElement<StateGroupKey>
        public object Filter : PipelineElement<StateGroupFilter>
        public object AQueue : PipelineElement<me.y9san9.aqueue.AQueue>
        public object Storage : PipelineElement<TelegramStorage>
        public object StateList : PipelineElement<UpdateStateList>
    }
}
