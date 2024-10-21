package me.y9san9.ksm.telegram.routing

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.update.MessageUpdate
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import me.y9san9.ksm.telegram.TelegramFSM
import me.y9san9.ksm.telegram.base.TelegramFSMBase.Subject
import me.y9san9.ksm.telegram.state.data.StateData
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.setSubject
import me.y9san9.pipeline.subject

public interface TelegramStorage {
    public suspend fun restore(
        bot: TelegramBot,
        update: MessageUpdate
    ): StateData.Map?

    public suspend fun save(
        bot: TelegramBot,
        update: MessageUpdate,
        data: StateData.Map?
    )

    public class InMemory : TelegramStorage {
        private val map = mutableMapOf<Long, StateData.Map?>()
        private val mutex = Mutex()

        override suspend fun restore(bot: TelegramBot, update: MessageUpdate): StateData.Map? {
            return mutex.withLock { map[update.data.chat.id.chatId.long] }
        }

        override suspend fun save(bot: TelegramBot, update: MessageUpdate, data: StateData.Map?) {
            mutex.withLock { map[update.data.chat.id.chatId.long] = data }
        }
    }
}

public var StateRouting.storage: TelegramStorage
    get() = context.subject.require(Subject.Storage)
    set(value) { context.setSubject(Subject.Storage, value) }
