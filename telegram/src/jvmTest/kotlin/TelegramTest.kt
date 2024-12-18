@file:OptIn(RiskFeature::class)

import dev.inmo.tgbotapi.extensions.api.send.reply
import dev.inmo.tgbotapi.extensions.api.send.sendMessage
import dev.inmo.tgbotapi.extensions.api.telegramBot
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.text
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.serialization.json.Json
import me.y9san9.ksm.telegram.buildTelegramFSM
import me.y9san9.ksm.telegram.json.goto
import me.y9san9.ksm.telegram.json.json
import me.y9san9.ksm.telegram.json.receive
import me.y9san9.ksm.telegram.privateMessage.routing.PrivateMessageRouting
import me.y9san9.ksm.telegram.state.StateName
import me.y9san9.ksm.telegram.state.routing.goto

val InitialState by StateName

fun PrivateMessageRouting.initialState() = state(InitialState) {
    name = InitialState

    handle {
        goto(StateB)
    }
}

val StateB by StateName

fun PrivateMessageRouting.stateB() = state(StateB) {
    transition {
        bot.sendMessage(userId, "Enter a number, please:")
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

val StateC by StateName

fun PrivateMessageRouting.stateC() = state(StateC) {
    transition {
        val int: Int = receive()
        bot.sendMessage(userId, "Your number incremented: ${int + 1}")
        goto(StateB)
    }
}

private fun PrivateMessageRouting.testStates() {
    initial = InitialState

    initialState()
    stateB()
    stateC()
}

suspend fun main() {
    val bot = telegramBot("7405359985:AAEY3Ulvn2l-bMKVv8nMvKUCO7dCyfZfxuI")

    val fsm = buildTelegramFSM {
        json = Json

        privateMessage {
            routing {
                testStates()
            }
        }
    }

    fsm.longPolling(bot)
}
