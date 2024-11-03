package me.y9san9.ksm.telegram.privateMessage.state

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.types.update.MessageUpdate
import me.y9san9.ksm.telegram.handler.base.UpdateHandlerBase.Bot
import me.y9san9.ksm.telegram.privateMessage.plugin.PrivateMessageHandlerPlugin.Message
import me.y9san9.ksm.telegram.privateMessage.plugin.PrivateMessageHandlerPlugin.Update
import me.y9san9.ksm.telegram.state.routing.StateRouter
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require

public object PrivateMessageHandler {
    @PipelineDsl
    public class Scope(public val context: PipelineContext) {
        @PipelineDsl
        public val router: StateRouter = StateRouter(context)

        @PipelineDsl
        public val bot: TelegramBot
            get() = context.require(Bot)

        @PipelineDsl
        public val update: MessageUpdate
            get() = context.require(Update)

        @PipelineDsl
        public val message: PrivateContentMessage<*>
            get() = context.require(Message)

        @PipelineDsl
        public val user: User
            get() = message.from
    }
}
