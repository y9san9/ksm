package me.y9san9.ksm.telegram

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.longPolling
import dev.inmo.tgbotapi.types.update.MessageUpdate
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import me.y9san9.ksm.telegram.group.StateGroup
import me.y9san9.ksm.telegram.handler.UpdateHandler
import me.y9san9.ksm.telegram.routing.FSMRouter
import me.y9san9.ksm.telegram.routing.StateDescriptor
import me.y9san9.ksm.telegram.routing.buildFSMRouter
import me.y9san9.ksm.telegram.transition.buildFSMTransition
import me.y9san9.ksm.telegram.state.State
import me.y9san9.ksm.telegram.transition.FSMTransition
import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.insertPhaseLast
import me.y9san9.pipeline.plugin.PipelinePlugin
import me.y9san9.pipeline.plugin.install
import me.y9san9.pipeline.proceed
import me.y9san9.pipeline.set

// TODO:
//  I don't care about extensibility of that place in the moment. Just make it working.
//  A list of Handlers is required if you want to handle update.
//  Special RegisterPhase should be done to register handlers before that Phase.
//  Default handler should be provided by TelegramFSM Plugin and it might be configured before
//  ConfigurePhase
//
public class TelegramFSM(public val context: PipelineContext) {
    public val pipeline: Pipeline get() = context.require(Pipeline)
    public val subject: PipelineContext get() = context.require(Subject)

    public suspend inline fun longPolling(
        bot: TelegramBot,
        crossinline block: FlowsUpdatesFilter.() -> Unit = {}
    ) {
        coroutineScope {
            bot.longPolling {
                launch { run(bot, messagesFlow) }
                block()
            }
        }
    }

    public suspend fun run(bot: TelegramBot, messageUpdates: Flow<MessageUpdate>) {
        pipeline.proceed(subject) {
            context[Bot] = bot
            context[UpdateFlow] = messageUpdates
        }
    }

    @PipelineDsl
    public class Builder(context: PipelineContext) {
        public val context: MutablePipelineContext = mutablePipelineContextOf(context)

        public val subject: MutablePipelineContext get() = context[Subject]

        public constructor() : this(PipelineContext.Empty) {
            context.install(TelegramFSM)
        }

        public fun build(): TelegramFSM {
            return TelegramFSM(context.toPipelineContext())
        }
    }

    public companion object Plugin : PipelinePlugin {
        override val name: String = "TelegramFSM"

        public val Pipeline: PipelineElement<Pipeline> by PipelineElement
        public val Subject: PipelineElement<PipelineContext> by PipelineElement

        public val DefaultHandler: PipelineElement<UpdateHandler> by PipelineElement
        public val Router: PipelineElement<FSMRouter> by PipelineElement
        public val Transition: PipelineElement<FSMTransition> by PipelineElement

        public val Bot: PipelineElement<TelegramBot> by PipelineElement
        public val UpdateFlow: PipelineElement<Flow<Update>> by PipelineElement
        public val HandlerList: PipelineElement<List<UpdateHandler>> by PipelineElement

        public val StateGroup: PipelineElement<StateGroup> by PipelineElement
        public val StateDescriptor: PipelineElement<StateDescriptor> by PipelineElement
        public val Update: PipelineElement<Update> by PipelineElement
        public val SelectedState: PipelineElement<State> by PipelineElement

        override fun apply(context: MutablePipelineContext) {
            context[HandlerList] = mutableListOf()
            context[Router] = buildFSMRouter()
            context[Transition] = buildFSMTransition()

            context.set(Pipeline) {
                insertPhaseLast(Setup)
                insertPhaseLast(Register)
                insertPhaseLast(AQueuePhase)
            }
        }
    }
}

public inline fun buildTelegramFSM(
    from: TelegramFSM? = null,
    block: TelegramFSM.Builder.() -> Unit = {}
): TelegramFSM {
    val builder = when (from) {
        null -> TelegramFSM.Builder()
        else -> TelegramFSM.Builder(from.context)
    }
    builder.block()
    return builder.build()
}

public inline fun TelegramFSM?.build(block: TelegramFSM.Builder.() -> Unit): TelegramFSM {
    return buildTelegramFSM(from = this, block = block)
}
