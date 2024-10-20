import dev.inmo.tgbotapi.extensions.api.send.reply
import dev.inmo.tgbotapi.extensions.api.telegramBot
import me.y9san9.ksm.telegram.*
import me.y9san9.ksm.telegram.routing.StateRouting
import me.y9san9.ksm.telegram.routing.state
import me.y9san9.ksm.telegram.state.*

suspend fun main() {
    val bot = telegramBot("7405359985:AAEY3Ulvn2l-bMKVv8nMvKUCO7dCyfZfxuI")

    val fsm = buildTelegramFSM {
        storage = TelegramStorage.InMemory()

        routing {
            initial = "A"

            testState()
        }
    }

    fsm.longPolling(bot)
}

private fun StateRouting.testState() {
    state {
        name = "A"

        transition {
            bot.reply(message, "Hello!")
        }

        handle {
            goto("B")
        }
    }

    state {
        name = "B"

        transition {
            bot.reply(message, "Goodbye!")
        }

        handle {
            goto("A")
        }
    }
}
