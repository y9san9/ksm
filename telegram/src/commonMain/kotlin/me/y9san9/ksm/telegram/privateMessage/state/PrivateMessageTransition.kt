package me.y9san9.ksm.telegram.privateMessage.state

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.UserId
import me.y9san9.ksm.telegram.plugin.handler.HandlerPlugin.Bot
import me.y9san9.ksm.telegram.plugin.handler.privateMessage.PrivateMessagePlugin.UserId
import me.y9san9.ksm.telegram.state.routing.StateRouter
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require

// todo: that transition should not be called when using sendInlineFSM
public object PrivateMessageTransition {
    @PipelineDsl
    public class Scope(public val context: PipelineContext) {
        @PipelineDsl
        public val router: StateRouter = StateRouter(context)

        @PipelineDsl
        public val userId: UserId
            get() = context.require(UserId)

        @PipelineDsl
        public val bot: TelegramBot
            get() = context.require(Bot)
    }
}
