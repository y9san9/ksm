package me.y9san9.ksm.state.runner

import me.y9san9.ksm.state.runner.plugin.StateRunnerBase
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.proceed

public suspend fun StateRunner.proceed(subject: PipelineContext): PipelineContext {
    return context.require(StateRunnerBase.Config.Pipeline).proceed(context, subject)
}
