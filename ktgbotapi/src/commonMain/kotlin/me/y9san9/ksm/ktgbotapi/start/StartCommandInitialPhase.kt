package me.y9san9.ksm.ktgbotapi.start

import me.y9san9.ksm.logger.log
import me.y9san9.ksm.logger.logger
import me.y9san9.ksm.logger.popLogger
import me.y9san9.ksm.logger.withTag
import me.y9san9.ksm.route.RoutePlugin
import me.y9san9.pipeline.PipelineSignal
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.with
import me.y9san9.pipeline.phase.PipelinePhase
import kotlin.math.log

public object StartCommandInitialPhase : PipelinePhase {
    override val name: String = "StartCommandInitial"

    override suspend fun proceed(context: PipelineContext): PipelineContext {
        if (RoutePlugin.InitialRoute !in context) return context
        val start = context.require(StartCommandPlugin.Pipeline)
        val tagged = context.withTag("StartCommand")
        tagged.log(start.prettyString())
        return start.proceed(tagged).with(PipelineSignal.Return).popLogger()
    }
}
