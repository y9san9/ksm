import dev.inmo.tgbotapi.extensions.api.telegramBot
import kotlinx.serialization.json.Json
import me.y9san9.ksm.telegram.buildTelegramFSM
import me.y9san9.ksm.telegram.callbackQuery.group.callbackQuery
import me.y9san9.ksm.telegram.callbackQuery.routing.CallbackQueryRouting
import me.y9san9.ksm.telegram.callbackQuery.routing.routing
import me.y9san9.ksm.telegram.callbackQuery.state.CallbackQueryHandler
import me.y9san9.ksm.telegram.group.base.UpdateGroupBase.name
import me.y9san9.ksm.telegram.json.json
import me.y9san9.ksm.telegram.state.StateName

suspend fun main() {
    val bot = telegramBot("7405359985:AAEY3Ulvn2l-bMKVv8nMvKUCO7dCyfZfxuI")

    val fsm = buildTelegramFSM {
        json = Json

        // bot.sendCallbackFSM("Csakcmasc")

        callbackQuery {
            //

            routing {

            }
        }
    }

    fsm.longPolling(bot)
}

private fun CallbackQueryRouting.testStates() {
    initial = MenuInitialState
}

val MenuInitialState by StateName

//private fun CallbackQueryRouting.menuState() = state()
