package me.y9san9.ksm.router.descriptor

import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.set

public data object StateDescriptorName : PipelineElement<String>

public val StateDescriptor.name: String? get() = context[StateDescriptorName]

public var StateDescriptorBuilder.name: String?
    get() = context[StateDescriptorName]
    set(value) { context[StateDescriptorName] = value }
