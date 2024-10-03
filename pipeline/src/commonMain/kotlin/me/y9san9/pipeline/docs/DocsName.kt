package me.y9san9.pipeline.docs

import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.subject.subject

public data object DocsName : PipelineElement<String>

public val PipelineDocs.name: String
    get() = context.require(DocsName) { "'name' was not provided for $context" }

public var DescribeScope.name: String
    get() = context.subject.require(DocsName) { "'name' was not provided for $context" }
    set(value) {
        context.subject {
            this[DocsName] = value
        }
    }
