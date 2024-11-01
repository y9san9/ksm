package me.y9san9.ksm.telegram.handler.base

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.types.update.MessageUpdate
import me.y9san9.ksm.telegram.group.UpdateStorage
import me.y9san9.ksm.telegram.privateMessage.base.PrivateMessagePhase
import me.y9san9.ksm.telegram.privateMessage.base.PrivateMessageStartResetPhase
import me.y9san9.ksm.telegram.routing.base.GotoLooperPhase
import me.y9san9.ksm.telegram.routing.base.GotoRoutePhase
import me.y9san9.ksm.telegram.routing.base.GotoRunPhase
import me.y9san9.ksm.telegram.routing.base.SavePhase
import me.y9san9.ksm.telegram.state.routing.UpdateStateList
import me.y9san9.ksm.telegram.state.UpdateState
import me.y9san9.ksm.telegram.state.routing.StateDescriptor
import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.buildPipeline
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.insertPhaseLast
import me.y9san9.pipeline.plugin.PipelinePlugin
import me.y9san9.pipeline.setSubject

public object TelegramUpdateHandlerBase : PipelinePlugin {
    override val name: String = "TelegramUpdateHandler"

    override fun apply(context: MutablePipelineContext) {
        context[Config.Pipeline] = Config.Pipeline.Default

        context.setSubject(Subject.GotoPipeline, Subject.GotoPipeline.Default)

        context.setSubject(Subject.PrivateMessage, Subject.PrivateMessage.Default)
        context.setSubject(Subject.CallbackQuery, Subject.CallbackQuery.Default)
    }

    public object Config {
        public object Pipeline : PipelineElement<me.y9san9.pipeline.Pipeline> {
            public val Default: me.y9san9.pipeline.Pipeline = buildPipeline {
                insertPhaseLast(RestorePhase)
                insertPhaseLast(FindByNamePhase)
                insertPhaseLast(PrivateMessagePhase)
                insertPhaseLast(CallbackQueryPhase)
                insertPhaseLast(RunPhase)
                insertPhaseLast(GotoLooperPhase)
                insertPhaseLast(SavePhase)
            }
        }
    }

    public object Subject {
        public object Update : PipelineElement<dev.inmo.tgbotapi.types.update.abstracts.Update>
        public object Bot : PipelineElement<TelegramBot>

        public object StateList : PipelineElement<UpdateStateList>
        public object Storage : PipelineElement<UpdateStorage>

        public object Descriptor : PipelineElement<StateDescriptor>
        public object State : PipelineElement<UpdateState>

        public object GotoContinuation : PipelineElement<me.y9san9.ksm.telegram.state.routing.GotoContinuation>
        public object GotoCommand : PipelineElement<me.y9san9.ksm.telegram.state.routing.GotoCommand>
        public object GotoPipeline : PipelineElement<Pipeline> {
            public val Default: Pipeline = buildPipeline {
                insertPhaseLast(GotoRoutePhase)
                insertPhaseLast(GotoRunPhase)
            }
        }

        public object PrivateMessage : PipelineElement<Pipeline> {
            public val Default: Pipeline = buildPipeline {
                insertPhaseLast(PrivateMessageStartResetPhase)
            }
        }
        public object PrivateMessageUserId : PipelineElement<UserId>
        public object PrivateMessageUpdate : PipelineElement<MessageUpdate>
        public object PrivateMessageData : PipelineElement<PrivateContentMessage<*>>

        public object CallbackQuery : PipelineElement<Pipeline> {
            public val Default: Pipeline = buildPipeline()
        }
        public object CallbackQueryUpdate : PipelineElement<dev.inmo.tgbotapi.types.update.CallbackQueryUpdate>
        public object CallbackQueryChatId : PipelineElement<ChatIdentifier>
        public object CallbackQueryMessageId : PipelineElement<MessageId>
        public object CallbackQueryInlineMessageId : PipelineElement<InlineMessageId>
    }
}
