package me.y9san9.ksm.telegram.callbackQuery.group

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.InlineMessageId
import dev.inmo.tgbotapi.types.MessageId
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import me.y9san9.ksm.telegram.callbackQuery.plugin.CallbackQueryPlugin
import me.y9san9.ksm.telegram.group.UpdateStorage
import me.y9san9.ksm.telegram.state.data.StateData
import me.y9san9.pipeline.context.PipelineContext

public interface CallbackQueryStorage {
    public suspend fun restore(
        bot: TelegramBot,
        chatId: ChatIdentifier?,
        messageId: MessageId?,
        inlineMessageId: InlineMessageId?
    ): StateData.Map?

    public suspend fun save(
        bot: TelegramBot,
        chatId: ChatIdentifier?,
        messageId: MessageId?,
        inlineMessageId: InlineMessageId?,
        data: StateData.Map?
    )

    public class InMemory : CallbackQueryStorage {
        private val map = mutableMapOf<Any?, StateData.Map?>()
        private val mutex = Mutex()

        override suspend fun restore(
            bot: TelegramBot,
            chatId: ChatIdentifier?,
            messageId: MessageId?,
            inlineMessageId: InlineMessageId?
        ): StateData.Map? {
            mutex.withLock {
                return map[messageId ?: inlineMessageId]
            }
        }

        override suspend fun save(
            bot: TelegramBot,
            chatId: ChatIdentifier?,
            messageId: MessageId?,
            inlineMessageId: InlineMessageId?,
            data: StateData.Map?
        ) {
            mutex.withLock {
                map[messageId ?: inlineMessageId] = data
            }
        }
    }
}

public fun CallbackQueryStorage.toUpdateStorage(): UpdateStorage {
    val value = this

    return object : UpdateStorage {
        override suspend fun restore(bot: TelegramBot, context: PipelineContext): StateData.Map? {
            val chatId = context[CallbackQueryPlugin.ChatId]
            val messageId = context[CallbackQueryPlugin.MessageId]
            val inlineMessageId = context[CallbackQueryPlugin.InlineMessageId]
            return value.restore(bot, chatId, messageId, inlineMessageId)
        }
        override suspend fun save(bot: TelegramBot, context: PipelineContext, data: StateData.Map?) {
            val chatId = context[CallbackQueryPlugin.ChatId]
            val messageId = context[CallbackQueryPlugin.MessageId]
            val inlineMessageId = context[CallbackQueryPlugin.InlineMessageId]
            value.save(bot, chatId, messageId, inlineMessageId, data)
        }
    }
}
