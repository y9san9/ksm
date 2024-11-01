package me.y9san9.ksm.telegram.privateMessage.group

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.types.update.MessageUpdate
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import me.y9san9.ksm.telegram.group.UpdateStorage
import me.y9san9.ksm.telegram.state.data.StateData

public interface PrivateMessageStorage {
    public suspend fun restore(
        bot: TelegramBot,
        update: MessageUpdate,
        message: PrivateContentMessage<*>
    ): StateData.Map?

    public suspend fun save(
        bot: TelegramBot,
        update: MessageUpdate,
        message: PrivateContentMessage<*>,
        data: StateData.Map?
    )

    public class InMemory : PrivateMessageStorage {
        private val map = mutableMapOf<Long, StateData.Map?>()
        private val mutex = Mutex()

        override suspend fun restore(
            bot: TelegramBot,
            update: MessageUpdate,
            message: PrivateContentMessage<*>
        ): StateData.Map? {
            return mutex.withLock { map[update.data.chat.id.chatId.long] }
        }

        override suspend fun save(
            bot: TelegramBot,
            update: MessageUpdate,
            message: PrivateContentMessage<*>,
            data: StateData.Map?
        ) {
            mutex.withLock { map[update.data.chat.id.chatId.long] = data }
        }
    }
}

public fun PrivateMessageStorage.toUpdateStorage(): UpdateStorage {
    val value = this
    return object : UpdateStorage {
        override suspend fun restore(bot: TelegramBot, update: Update): StateData.Map? {
            return value.restore(bot, update as MessageUpdate, update.data as PrivateContentMessage<*>)
        }
        override suspend fun save(bot: TelegramBot, update: Update, data: StateData.Map?) {
            value.save(bot, update as MessageUpdate, update.data as PrivateContentMessage<*>, data)
        }
    }
}
