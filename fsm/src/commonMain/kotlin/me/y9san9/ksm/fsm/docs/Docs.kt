package me.y9san9.ksm.fsm.docs

import me.y9san9.ksm.fsm.FSM
import me.y9san9.ksm.fsm.FSMPipeline
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.docs.PipelineDocs
import me.y9san9.pipeline.docs.docs

public fun FSM.docs(): PipelineDocs {
    return context.require(FSMPipeline).docs(context)
}
