package me.y9san9.pipeline.docs

import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.subject.subject

public data object DocsDescription : PipelineElement<String>

public val PipelineDocs.description: String?
    get() = context[DocsDescription]

public var DescribeScope.description: String?
    get() = context.subject[DocsDescription]
    set(value) {
        context.subject {
            this[DocsDescription] = value
        }
    }
