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
    override val name: String = "UpdateStateGroupBase"

    public val Name: PipelineElement<String> by PipelineElement
    public val Key: PipelineElement<UpdateKey> by PipelineElement
    public val Filter: PipelineElement<UpdateFilter> by PipelineElement
    public val AQueue: PipelineElement<AQueue> by PipelineElement
    public val Storage: PipelineElement<UpdateStorage> by PipelineElement
    public val StateList: PipelineElement<UpdateStateList> by PipelineElement

    override fun apply(context: MutablePipelineContext) {
        context[AQueue] = AQueue()
    }
}
