package me.y9san9.ksm.ktgbotapi.start

import me.y9san9.ksm.logger.log
import me.y9san9.ksm.logger.logger
import me.y9san9.ksm.logger.withLogger
import me.y9san9.ksm.logger.withTag
import me.y9san9.ksm.route.RoutePlugin
import me.y9san9.ksm.state.*
import me.y9san9.pipeline.PipelineSignal
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.with
import me.y9san9.pipeline._PipelineRunnable

public object StartCommandPhase : _PipelineRunnable {
    override val name: String = "StartCommand"

    override suspend fun proceed(context: PipelineContext): PipelineContext {
        val list = context.require(RoutePlugin.List)

        val initialName = list.initial.require(StatePlugin.StateName)
        val initialParameters = stateDataOf(StateRestore.STATE_DATA to StateData.Map.Empty)

        val descriptor = StateDescriptor(initialName, initialParameters)

        val goto = context.require(StatePlugin.GotoPipeline)
        val tagged = context.withTag("Goto")
        tagged.log(goto.prettyString())
        goto.proceed(PipelineContext.with(StatePlugin.StateDescriptor, descriptor).withLogger(tagged.logger))
        return context.with(PipelineSignal.Return)
    }
}
