package me.y9san9.ksm.telegram.transition

import me.y9san9.ksm.telegram.routing.GotoCommand
import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.insertPhaseLast
import me.y9san9.pipeline.plugin.PipelinePlugin
import me.y9san9.pipeline.plugin.install
import me.y9san9.pipeline.proceed
import me.y9san9.pipeline.set
import me.y9san9.pipeline.subject

// todo: implement separate RoutePipeline and add extensions:
//  - fsm.router.goto('groupName', StateDescriptor)
//  - fsm.router.privateMessage.goto(groupName: String?, UserId, StateDescriptor)
//  - fsm.router.callbackQuery.goto(groupName: String, ChatId, MessageId, StateDescriptor)
public data class FSMTransition(public val context: PipelineContext) {
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
            context.install(FSMTransition)
        }

        public fun build(): FSMTransition {
            return FSMTransition(context.toPipelineContext())
        }
    }

    public companion object Plugin : PipelinePlugin {
        override val name: String = "FSMTransition"

        public val Pipeline: PipelineElement<Pipeline> by PipelineElement
        public val GotoCommand: PipelineElement<GotoCommand> by PipelineElement
        public val GotoPipeline: PipelineElement<Pipeline> by PipelineElement
        public val Continuation: PipelineElement<StateContinuation> by PipelineElement

        override fun apply(context: MutablePipelineContext) {
            context.set(Pipeline) {
                subject.set(GotoPipeline) {
                    insertPhaseLast(TransitionRouterPhase)
                    insertPhaseLast(TransitionRunPhase)
                }

                insertPhaseLast(GotoPhase)
                insertPhaseLast(SavePhase)
            }
        }
    }
}

public inline fun buildFSMTransition(
    from: FSMTransition? = null,
    block: FSMTransition.Builder.() -> Unit = {}
): FSMTransition {
    val builder = when (from) {
        null -> FSMTransition.Builder()
        else -> FSMTransition.Builder(from.context)
    }
    builder.block()
    return builder.build()
}

public inline fun MutablePipelineContext.set(
    element: PipelineElement<FSMTransition>,
    block: FSMTransition.Builder.() -> Unit
) {
    context[element] = buildFSMTransition(context[element], block)
}
