package me.y9san9.ksm.router.descriptor

import me.y9san9.ksm.state.data.StateData
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set

public data object StateDescriptorParameters : PipelineElement<StateData.Map>

public val StateDescriptor.parameters: StateData.Map
    get() = context.require(StateDescriptorParameters)

public var StateDescriptorBuilder.parameters: StateData.Map
    get() = context.require(StateDescriptorParameters)
    set(value) { context[StateDescriptorParameters] = value }
