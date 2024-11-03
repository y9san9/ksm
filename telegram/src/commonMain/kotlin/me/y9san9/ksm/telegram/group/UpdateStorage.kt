package me.y9san9.ksm.telegram.group

import dev.inmo.tgbotapi.bot.TelegramBot
import me.y9san9.ksm.telegram.group.base.UpdateStateGroupBase
import me.y9san9.ksm.telegram.group.base.UpdateStateGroupBase.Storage
import me.y9san9.ksm.telegram.state.data.StateData
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require

public interface UpdateStorage {
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

public val UpdateStateGroup.storage: UpdateStorage
    get() = context.require(Storage)
