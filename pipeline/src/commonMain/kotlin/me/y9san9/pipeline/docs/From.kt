package me.y9san9.pipeline.docs

import me.y9san9.pipeline.context.plus
import me.y9san9.pipeline.subject.subject

public fun DescribeScope.from(docs: PipelineDocs) {
    context.subject += docs.context
}
