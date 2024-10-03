package me.y9san9.ksm.events

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.y9san9.ksm.logger.log
import me.y9san9.ksm.logger.logger
import me.y9san9.ksm.logger.withLogger
import me.y9san9.ksm.logger.withTag
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.with
import me.y9san9.pipeline._PipelineRunnable
import kotlin.coroutines.EmptyCoroutineContext

public object EventsPipelinePhase : _PipelineRunnable {
    override val name: String = "EventPipeline"

    override suspend fun proceed(context: PipelineContext): PipelineContext {
        val flow = context.require(EventsPlugin.Flow)
        val pipeline = context.require(EventsPlugin.Pipeline)
        val keyPipeline = context.require(EventsPlugin.KeyPipeline)

        val taggedKey = context.withTag("Key")
        taggedKey.log(keyPipeline.prettyString())
        val taggedEvent = context.withTag("Event")
        taggedEvent.log(pipeline.prettyString())

        val queue = context.require(EventsPlugin.Queue)
        val coroutineContext = context[EventsPlugin.CoroutineContext] ?: EmptyCoroutineContext

        channelFlow {
            flow.collect { eventContext ->
                eventContext.require(EventsPlugin.Event)
                println()

                launch(start = CoroutineStart.UNDISPATCHED) {
                    val key = keyPipeline.proceed(eventContext.withLogger(taggedKey.logger))[EventsPlugin.Key]

                    val result = queue.execute(key, coroutineContext) {
                        pipeline.proceed(context = eventContext.with(EventsPlugin.Key, key).withLogger(taggedEvent.logger))
                    }
                    send(result)
                }
            }
        }.collect()

        return context
    }
}
