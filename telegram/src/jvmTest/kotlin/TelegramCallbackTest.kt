import dev.inmo.tgbotapi.extensions.api.telegramBot
import dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard
import dev.inmo.tgbotapi.utils.row
import kotlinx.serialization.json.Json
import me.y9san9.ksm.telegram.buildTelegramFSM
import me.y9san9.ksm.telegram.callbackQuery.group.callbackQuery
import me.y9san9.ksm.telegram.callbackQuery.routing.CallbackQueryRouting
import me.y9san9.ksm.telegram.callbackQuery.routing.routing
import me.y9san9.ksm.telegram.callbackQuery.state.*
import me.y9san9.ksm.telegram.json.goto
import me.y9san9.ksm.telegram.json.json
import me.y9san9.ksm.telegram.state.StateName
import me.y9san9.ksm.telegram.state.routing.goto

suspend fun main() {
    val bot = telegramBot("7405359985:AAEY3Ulvn2l-bMKVv8nMvKUCO7dCyfZfxuI")

    val fsm = buildTelegramFSM {
        json = Json

        // bot.sendCallbackFSM("Menu")

        callbackQuery {

            routing {
                testStates()
            }
        }
    }

    fsm.longPolling(bot)
}

private fun CallbackQueryRouting.testStates() {
    initial = MenuInitialState

    menuState()
    menuDeletedState()
}

val MenuInitialState by StateName

fun CallbackQueryRouting.menuState() = state(MenuInitialState) {
    val yes by CallbackData
    val no by CallbackData

    message {
        text = "Do you want do delete your Telegram Account?"

        keyboard = inlineKeyboard {
            row {
                +yes.button(text = "Yes!")
                +no.button(text = "No!")
            }
        }
    }

    handle {

        // external.goto(StateName, StateData).privateMessages(UserId)
        // external.goto(StateName, StateData).callbackQuery[MainMenu](ChatId, MessageId?)

        // privateMessage(UserId).goto(StateName, StateData)
        // callbackQuery(ChatId, MessageId?).goto(StateName, StateData)

        case(yes) {
            goto(MenuDeletedState)
        }
        case(no) {
            goto(MenuInitialState)
        }
    }
}

val MenuDeletedState by StateName

fun CallbackQueryRouting.menuDeletedState() = state(MenuDeletedState) {
    message {
        text = "Menu deleted!"
    }
}
