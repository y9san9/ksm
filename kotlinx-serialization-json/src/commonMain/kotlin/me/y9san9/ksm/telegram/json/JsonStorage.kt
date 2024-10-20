package me.y9san9.ksm.telegram.json

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.update.MessageUpdate
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import me.y9san9.ksm.telegram.TelegramFSM
import me.y9san9.ksm.telegram.TelegramStorage
import me.y9san9.ksm.telegram.state.data.StateData
import me.y9san9.ksm.telegram.storage

public interface JsonTelegramStorage {
    public val json: Json get() = Json

    public suspend fun restore(
        bot: TelegramBot,
        update: MessageUpdate
    ): String?

    public suspend fun save(
        bot: TelegramBot,
        update: MessageUpdate,
        data: String?
    )
}

public var TelegramFSM.Builder.jsonStorage: JsonTelegramStorage
    @Deprecated("Use setter", level = DeprecationLevel.HIDDEN) get() = TODO()
    set(value) {
        storage = object : TelegramStorage {
            override suspend fun restore(
                bot: TelegramBot,
                update: MessageUpdate
            ): StateData.Map? {
                val string = value.restore(bot, update) ?: return null
                return value.json.parseToJsonElement(string).toStateData() as StateData.Map
            }

            override suspend fun save(
                bot: TelegramBot,
                update: MessageUpdate,
                data: StateData.Map?
            ) {
                data ?: return value.save(bot, update, data = null)
                val string = value.json.encodeToString(data.toJsonElement())
                value.save(bot, update, string)
            }
        }
    }
