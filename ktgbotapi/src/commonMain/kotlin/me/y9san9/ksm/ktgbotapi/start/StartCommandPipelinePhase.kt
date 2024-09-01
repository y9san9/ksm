package me.y9san9.ksm.ktgbotapi.start

import dev.inmo.tgbotapi.extensions.utils.extensions.raw.text
import dev.inmo.tgbotapi.utils.RiskFeature
import me.y9san9.ksm.events.EventsPlugin
import me.y9san9.ksm.ktgbotapi.event.MessageEvent
import me.y9san9.ksm.logger.log
import me.y9san9.ksm.logger.popLogger
import me.y9san9.ksm.logger.withTag
import me.y9san9.pipeline.PipelineSignal
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.with
import me.y9san9.pipeline.phase.PipelinePhase

public object StartCommandPipelinePhase : PipelinePhase {
    override val name: String = "StartCommandPipeline"

    @OptIn(RiskFeature::class)
    override suspend fun proceed(context: PipelineContext): PipelineContext {
        val event = context.require(EventsPlugin.Event) as MessageEvent
        val text = event.message.text
        if (text != null && !text.startsWith("/start")) {
            return context
        }
        val start = context.require(StartCommandPlugin.Pipeline)
        val tagged = context.withTag("StartCommand")
        tagged.log(start.prettyString())
        return start.proceed(tagged).with(PipelineSignal.Return).popLogger()
    }
}
