package me.y9san9.ksm.telegram.group.base

import me.y9san9.aqueue.AQueue
import me.y9san9.ksm.telegram.group.UpdateFilter
import me.y9san9.ksm.telegram.group.UpdateKey
import me.y9san9.ksm.telegram.group.UpdateStorage
import me.y9san9.ksm.telegram.state.routing.UpdateStateList
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.plugin.PipelinePlugin

public object UpdateStateGroupBase : PipelinePlugin {
    override val name: String = "StateGroupBase"

    override fun apply(context: MutablePipelineContext) {
        context[Config.AQueue] = AQueue()
    }

    public object Config {
        public object Name : PipelineElement<String>
        public object Key : PipelineElement<UpdateKey>
        public object Filter : PipelineElement<UpdateFilter>
        public object AQueue : PipelineElement<me.y9san9.aqueue.AQueue>
        public object Storage : PipelineElement<UpdateStorage>
        public object StateList : PipelineElement<UpdateStateList>
    }
}
