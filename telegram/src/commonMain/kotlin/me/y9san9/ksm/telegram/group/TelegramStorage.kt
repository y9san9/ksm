package me.y9san9.ksm.telegram.group

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.update.MessageUpdate
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import me.y9san9.ksm.telegram.group.base.StateGroupBase
import me.y9san9.ksm.telegram.state.data.StateData
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set

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

    public class InMemory : TelegramStorage {
        private val map = mutableMapOf<Long, StateData.Map?>()
        private val mutex = Mutex()

        override suspend fun restore(bot: TelegramBot, update: Update): StateData.Map? {
            update as MessageUpdate
            return mutex.withLock { map[update.data.chat.id.chatId.long] }
        }

        override suspend fun save(bot: TelegramBot, update: Update, data: StateData.Map?) {
            update as MessageUpdate
            mutex.withLock { map[update.data.chat.id.chatId.long] = data }
        }
    }
}

public val StateGroup.storage: TelegramStorage
    get() = context.require(StateGroupBase.Config.Storage)

public var StateGroup.Builder.storage: TelegramStorage
    get() = context.require(StateGroupBase.Config.Storage)
    set(value) { context[StateGroupBase.Config.Storage] = value }
