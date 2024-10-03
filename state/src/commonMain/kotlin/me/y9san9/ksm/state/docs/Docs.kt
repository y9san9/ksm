package me.y9san9.ksm.state.docs

import me.y9san9.ksm.state.runner.StateRunner
import me.y9san9.ksm.state.runner.StateRunnerPipeline
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.docs.PipelineDocs
import me.y9san9.pipeline.docs.docs

public fun StateRunner.docs(): PipelineDocs {
    return context.require(StateRunnerPipeline).docs(context)
}
