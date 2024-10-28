package me.y9san9.ksm.telegram.group

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.update.abstracts.Update
import me.y9san9.ksm.telegram.group.base.UpdateGroupBase
import me.y9san9.ksm.telegram.state.data.StateData
import me.y9san9.pipeline.context.require

public interface TelegramStorage {
    public suspend fun restore(
        bot: TelegramBot,
        update: Update
    ): StateData.Map?

    public suspend fun save(
        bot: TelegramBot,
        update: Update,
        data: StateData.Map?
    )
}

public val UpdateStateGroup.storage: TelegramStorage
    get() = context.require(UpdateGroupBase.Config.Storage)
