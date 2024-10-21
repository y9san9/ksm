package me.y9san9.ksm.telegram.routing

import me.y9san9.ksm.telegram.TelegramFSM
import me.y9san9.ksm.telegram.base.TelegramFSMBase
import me.y9san9.ksm.telegram.routing.base.StateListBase
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.plugin.install

@PipelineDsl
public open class StateRouting(
    public val context: MutablePipelineContext
) {
    public constructor() : this(mutablePipelineContextOf()) {
        context.install(StateListBase)
    }

    public fun toStateList(): StateList {
        return StateList(context.toPipelineContext())
    }
}

@PipelineDsl
public fun TelegramFSM.Builder.routing(block: StateRouting.() -> Unit) {
    val routing = StateRouting()
    routing.block()
    context[TelegramFSMBase.Subject.StateLists] = context.require(TelegramFSMBase.Subject.StateLists) + routing.toStateList()
}
