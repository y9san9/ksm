package me.y9san9.pipeline.docs

import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.subject.subject

public data object DocsSubjectDescription : PipelineElement<List<String>>

public val PipelineDocs.subjectDescription: List<String>?
    get() = context[DocsSubjectDescription]

public var DescribeScope.subjectDescription: List<String>?
    get() = context.subject[DocsSubjectDescription]
    set(value) {
        context.subject {
            this[DocsSubjectDescription] = value
        }
    }
