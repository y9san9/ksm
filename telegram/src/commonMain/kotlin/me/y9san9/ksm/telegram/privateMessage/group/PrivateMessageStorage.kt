package me.y9san9.ksm.telegram.privateMessage.group

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.types.update.MessageUpdate
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import me.y9san9.ksm.telegram.group.UpdateStorage
import me.y9san9.ksm.telegram.privateMessage.plugin.PrivateMessagePlugin.Message
import me.y9san9.ksm.telegram.privateMessage.plugin.PrivateMessagePlugin.Update
import me.y9san9.ksm.telegram.state.data.StateData
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require

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
        override suspend fun restore(bot: TelegramBot, context: PipelineContext): StateData.Map? {
            val update = context.require(Update)
            val message = context.require(Message)
            return value.restore(bot, update, message)
        }

        override suspend fun save(bot: TelegramBot, context: PipelineContext, data: StateData.Map?) {
            val update = context.require(Update)
            val message = context.require(Message)
            value.save(bot, update, message, data)
        }
    }
}
