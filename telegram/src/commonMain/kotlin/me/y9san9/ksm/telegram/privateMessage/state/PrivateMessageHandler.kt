package me.y9san9.ksm.telegram.privateMessage.state

import me.y9san9.ksm.telegram.state.UpdateHandler
import me.y9san9.ksm.telegram.state.base.UpdateStateBase
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.set

public object PrivateMessageHandler {
    @PipelineDsl
    public class Scope(context: PipelineContext) : UpdateHandler.Scope(context)
}


@PipelineDsl
public fun PrivateMessageState.Builder.handle(block: suspend PrivateMessageHandler.Scope.() -> Unit) {
    context[UpdateStateBase.Config.Handler] = UpdateHandler { scope ->
        PrivateMessageHandler.Scope(scope.context).block()
    }
}
