package me.y9san9.ksm.ktgbotapi.storage

import dev.inmo.tgbotapi.types.UserId

public interface TelegramStorage {
    public suspend fun save(key: UserId, data: String)
    public suspend fun restore(key: UserId): String?

    public class Memory : TelegramStorage {
        private val map = mutableMapOf<UserId, String>()

        override suspend fun restore(key: UserId): String? {
            return map[key]
        }

        override suspend fun save(key: UserId, data: String) {
            map[key] = data
        }
    }
}
