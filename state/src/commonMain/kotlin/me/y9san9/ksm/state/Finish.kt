package me.y9san9.ksm.state

import me.y9san9.ksm.state.runner.plugin.StateRunnerBase
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.subject.subject

public suspend fun StateHandler.Scope.finish(subject: PipelineContext): Nothing {
    context.subject.require(StateRunnerBase.Subject.StateContinuation).finish(subject)
}
