package me.y9san9.ksm.router.docs

import me.y9san9.ksm.router.StateRouter
import me.y9san9.ksm.router.StateRouterPipeline
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.docs.PipelineDocs
import me.y9san9.pipeline.docs.docs

public fun StateRouter.docs(): PipelineDocs {
    return context.require(StateRouterPipeline).docs(context)
}
