package me.y9san9.ksm.telegram.state

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.update.abstracts.Update
import me.y9san9.ksm.telegram.TelegramFSM.Plugin.Bot
import me.y9san9.ksm.telegram.TelegramFSM.Plugin.Update
import me.y9san9.ksm.telegram.state.routing.StateRouter
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require

public fun interface StateTransition {
    public suspend fun run(context: PipelineContext)

    public interface Scope : StateRouter {
        override val context: PipelineContext

        @PipelineDsl
        public val bot: TelegramBot get() = context.require(Bot)

        @PipelineDsl
        public val update: Update get() = context.require(Update)
    }
}
