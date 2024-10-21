import dev.inmo.tgbotapi.extensions.api.send.reply
import dev.inmo.tgbotapi.extensions.api.telegramBot
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.text
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.serialization.json.Json
import me.y9san9.ksm.telegram.*
import me.y9san9.ksm.telegram.json.goto
import me.y9san9.ksm.telegram.json.json
import me.y9san9.ksm.telegram.json.receive
import me.y9san9.ksm.telegram.routing.*
import me.y9san9.ksm.telegram.state.*

suspend fun main() {
    val bot = telegramBot("7405359985:AAEY3Ulvn2l-bMKVv8nMvKUCO7dCyfZfxuI")

    val fsm = buildTelegramFSM {
        storage = TelegramStorage.InMemory()
        json = Json

        routing {
            initial = "A"

            testState()
        }
    }

    fsm.longPolling(bot)
}

@OptIn(RiskFeature::class)
private fun StateRouting.testState() {
    state {
        name = "A"

        handle {
            goto("B")
        }
    }

    state {
        name = "B"

        transition {
            bot.reply(message, "Enter a number, please:")
        }

        handle {
            val text = message.text
            if (text == null) {
                bot.reply(message, "Enter a valid number!")
                return@handle
            }

            val int = text.toIntOrNull()
            if (int == null) {
                bot.reply(message, "Enter a valid number!")
                return@handle
            }

            goto("C", int)
        }
    }

    state {
        name = "C"

        transition {
            val int: Int = receive()
            bot.reply(message, "Your number incremented: ${int + 1}")
            goto("B")
        }
    }
}
