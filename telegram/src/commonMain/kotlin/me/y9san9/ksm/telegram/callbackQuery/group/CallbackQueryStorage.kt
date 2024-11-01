package me.y9san9.ksm.telegram.callbackQuery.group

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.inline_message_id
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.message
import dev.inmo.tgbotapi.types.update.CallbackQueryUpdate
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import me.y9san9.ksm.telegram.group.UpdateStorage
import me.y9san9.ksm.telegram.state.data.StateData

public interface CallbackQueryStorage {
    public suspend fun restore(
        bot: TelegramBot,
        update: CallbackQueryUpdate
    ): StateData.Map?

    public suspend fun save(
        bot: TelegramBot,
        update: CallbackQueryUpdate,
        data: StateData.Map?
    )

    @OptIn(RiskFeature::class)
    public class InMemory : CallbackQueryStorage {
        private val map = mutableMapOf<Any?, StateData.Map?>()
        private val mutex = Mutex()

        override suspend fun save(bot: TelegramBot, update: CallbackQueryUpdate, data: StateData.Map?) {
            mutex.withLock {
                map[update.data.message ?: update.data.inline_message_id] = data
            }
        }

        override suspend fun restore(bot: TelegramBot, update: CallbackQueryUpdate): StateData.Map? {
            return mutex.withLock {
                map[update.data.message ?: update.data.inline_message_id]
            }
        }
    }
}

public fun CallbackQueryStorage.toUpdateStorage(): UpdateStorage {
    val value = this
    return object : UpdateStorage {
        override suspend fun restore(bot: TelegramBot, update: Update): StateData.Map? {
            return value.restore(bot, update as CallbackQueryUpdate)
        }
        override suspend fun save(bot: TelegramBot, update: Update, data: StateData.Map?) {
            value.save(bot, update as CallbackQueryUpdate, data)
        }
    }
}
