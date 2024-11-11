package me.y9san9.ksm.telegram.group

import dev.inmo.tgbotapi.bot.TelegramBot
import me.y9san9.ksm.telegram.state.data.StateData
import me.y9san9.pipeline.context.PipelineContext

public interface TelegramStorage {
    public suspend fun restore(
        bot: TelegramBot,
        context: PipelineContext
    ): StateData.Map?

    public suspend fun save(
        bot: TelegramBot,
        context: PipelineContext,
        data: StateData.Map?
    )
}
