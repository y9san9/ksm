@file:OptIn(RiskFeature::class)

import dev.inmo.tgbotapi.extensions.api.send.reply
import dev.inmo.tgbotapi.extensions.api.send.sendMessage
import dev.inmo.tgbotapi.extensions.api.telegramBot
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.text
import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.types.update.MessageUpdate
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.serialization.json.Json
import me.y9san9.ksm.telegram.*
import me.y9san9.ksm.telegram.json.goto
import me.y9san9.ksm.telegram.json.json
import me.y9san9.ksm.telegram.json.receive
import me.y9san9.ksm.telegram.messages.messages
import me.y9san9.ksm.telegram.routing.*
import me.y9san9.ksm.telegram.state.*

val InitialState by StateRoute

fun StateRouting.initialState() = state(InitialState) {
    handle {
        goto(StateB)
    }
}

val StateB by StateRoute

fun StateRouting.stateB() = state(StateB) {
    transition {
        bot.sendMessage(message.chat, "Enter a number, please:")
    }

    handle {
        val int = message.text?.toIntOrNull()

        if (int == null) {
            bot.reply(message, "Enter a valid number!")
            return@handle
        }

        goto(StateC, int)
    }
}

val StateC by StateRoute

fun StateRouting.stateC() = state(StateC) {
    transition {
        val int: Int = receive()
        bot.reply(message, "Your number incremented: ${int + 1}")
        goto(StateB)
    }
}

private fun StateRouting.testStates() {
    initial = InitialState

    initialState()
    stateB()
    stateC()
}

suspend fun main() {
    val bot = telegramBot("7405359985:AAEY3Ulvn2l-bMKVv8nMvKUCO7dCyfZfxuI")

    val fsm = buildTelegramFSM {
        json = Json

        messages {
            routing {
                testStates()
            }
        }
    }

    fsm.longPolling(bot)
}
