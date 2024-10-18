package me.y9san9.ksm.telegram

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.update.MessageUpdate
import me.y9san9.ksm.state.data.StateData

public interface TelegramStorage {
    public suspend fun restore(
        bot: TelegramBot,
        update: MessageUpdate
    ): StateData.Map?

    public suspend fun save(
        bot: TelegramBot,
        update: MessageUpdate,
        data: StateData?
    )
}
