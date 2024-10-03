package me.y9san9.pipeline.docs

import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.PipelineBuilder
import me.y9san9.pipeline.buildPipeline
import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.docs.plugin.PipelineDocsPlugin
import me.y9san9.pipeline.plugin.dependsOn
import me.y9san9.pipeline.proceed
import me.y9san9.pipeline.subject.subject
import kotlin.coroutines.Continuation
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.startCoroutine

public object DocsPipeline : PipelineElement<Pipeline>

public fun Pipeline.docs(parent: PipelineContext = PipelineContext.Empty): PipelineDocs {
    this.context.dependsOn(PipelineDocsPlugin)

    val context = this.context.build { this.context += parent }
    val pipeline = this.context.require(DocsPipeline)

    val continuation = object : Continuation<PipelineContext> {
        var result: Result<PipelineContext>? = null
        override val context = EmptyCoroutineContext
        override fun resumeWith(result: Result<PipelineContext>) { this.result = result }
    }
    suspend { pipeline.proceed(context, PipelineContext.Empty) }.startCoroutine(continuation)

    val docs = continuation.result?.getOrThrow() ?: error("Documentation Pipeline must not to suspend!")

    return PipelineDocs.of(docs.subject)
}

public inline fun PipelineBuilder.docsPipeline(
    block: PipelineBuilder.() -> Unit = {}
) {
    context[DocsPipeline] = buildPipeline(context[DocsPipeline], block)
}
