package me.y9san9.pipeline.docs

import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.subject.subject

public data object DocsPhaseList : PipelineElement<List<PipelineDocs>>

public val PipelineDocs.phases: List<PipelineDocs>?
    get() = context[DocsPhaseList]

public var DescribeScope.phases: List<PipelineDocs>?
    get() = context.subject[DocsPhaseList]
    set(value) {
        context.subject {
            this[DocsPhaseList] = value
        }
    }
