package me.y9san9.ksm.telegram.state

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.update.abstracts.Update
import me.y9san9.ksm.telegram.plugin.handler.HandlerPlugin.Bot
import me.y9san9.ksm.telegram.plugin.handler.HandlerPlugin.Update
import me.y9san9.ksm.telegram.state.routing.StateRouter
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require

public fun interface UpdateHandler {
    public suspend fun run(scope: Scope)

    @PipelineDsl
    public class Scope(public val context: PipelineContext) {
        @PipelineDsl
        public val bot: TelegramBot
            get() = context.require(Bot)

        @PipelineDsl
        public val router: StateRouter
            get() = StateRouter(context)

        @PipelineDsl
        public val update: Update
            get() = context.require(Update)
    }
}
