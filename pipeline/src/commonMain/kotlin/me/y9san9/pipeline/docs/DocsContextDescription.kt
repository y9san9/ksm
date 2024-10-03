package me.y9san9.pipeline.docs

import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.subject.subject

public data object DocsContextDescription : PipelineElement<List<String>>

public val PipelineDocs.contextDescription: List<String>?
    get() = context[DocsContextDescription]

public var DescribeScope.contextDescription: List<String>?
    get() = context.subject[DocsContextDescription]
    set(value) {
        context.subject {
            this[DocsContextDescription] = value
        }
    }
