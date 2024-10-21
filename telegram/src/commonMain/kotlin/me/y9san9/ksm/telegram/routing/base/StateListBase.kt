package me.y9san9.ksm.telegram.routing.base

import me.y9san9.ksm.telegram.routing.StateListFilter
import me.y9san9.ksm.telegram.routing.StateListKey
import me.y9san9.ksm.telegram.routing.TelegramStorage
import me.y9san9.ksm.telegram.state.State
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.plugin.PipelinePlugin

public object StateListBase : PipelinePlugin {
    override val name: String = "StateListBase"

    override fun apply(context: MutablePipelineContext) {
        context[Config.StateList] = emptyList()
    }

    public object Config {
        public object Initial : PipelineElement<String>
        public object StateList : PipelineElement<List<State>>
        public object Filter : PipelineElement<StateListFilter>
        public object Storage : PipelineElement<TelegramStorage>
        public object AQueue : PipelineElement<me.y9san9.aqueue.AQueue>
        public object Key : PipelineElement<StateListKey>
    }
}
