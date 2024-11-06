package me.y9san9.ksm.telegram.routing

import me.y9san9.ksm.telegram.routing.base.FSMRouterBase
import me.y9san9.ksm.telegram.routing.base.FSMRouterBase.Pipeline
import me.y9san9.ksm.telegram.state.base.UpdateStateBase.GotoCommand
import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.plugin.install
import me.y9san9.pipeline.proceed

// todo: implement separate RoutePipeline and add extensions:
//  - fsm.router.goto('groupName', StateDescriptor)
//  - fsm.router.privateMessage.goto(groupName: String?, UserId, StateDescriptor)
//  - fsm.router.callbackQuery.goto(groupName: String, ChatId, MessageId, StateDescriptor)
public class FSMRouter(public val context: PipelineContext) {
    public val pipeline: Pipeline get() = context.require(Pipeline)

    public suspend inline fun goto(
        command: GotoCommand,
        subject: PipelineContext = PipelineContext.Empty,
        block: MutablePipelineContext.() -> Unit = {}
    ) {
        pipeline.proceed(subject) {
            context[GotoCommand] = command
            block()
        }
    }

    @PipelineDsl
    public class Builder(context: PipelineContext) {
        public val context: MutablePipelineContext = mutablePipelineContextOf(context)

        public constructor() : this(PipelineContext.Empty) {
            context.install(FSMRouterBase)
        }

        public fun build(): FSMRouter {
            return FSMRouter(context.toPipelineContext())
        }
    }
}

public inline fun buildFSMRouter(
    from: FSMRouter? = null,
    block: FSMRouter.Builder.() -> Unit = {}
): FSMRouter {
    val builder = when (from) {
        null -> FSMRouter.Builder()
        else -> FSMRouter.Builder(from.context)
    }
    builder.block()
    return builder.build()
}

public inline fun MutablePipelineContext.set(
    element: PipelineElement<FSMRouter>,
    block: FSMRouter.Builder.() -> Unit
) {
    context[element] = buildFSMRouter(context[element], block)
}
