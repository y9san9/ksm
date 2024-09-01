import dev.inmo.tgbotapi.extensions.api.send.reply
import dev.inmo.tgbotapi.extensions.api.telegramBot
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.longPolling
import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import me.y9san9.ksm.ktgbotapi.buildBotController
import me.y9san9.ksm.ktgbotapi.event.MessageEvent
import me.y9san9.ksm.ktgbotapi.state.*

@Serializable
data class UserState(
    val foo: String,
    val bar: List<String>
)

suspend fun main(): Unit = coroutineScope {
    val bot = telegramBot(System.getenv("BOT_TOKEN"))

    val controller = buildBotController {

        routing(initialState = "StateA") {
            // todo: add automatic /start handler

            state("StateA") {
                transition {
                    bot.reply(message, "Привет! Это команда /start")
                }

                handle {
                    goto("StateB", data = UserState(foo = "foo", bar = listOf("bar")))
                }
            }

            state("StateB") {
                // Можем попросить что-то ввести
                transition {
                    val data: UserState = receive()
                    bot.reply(message, "This is transition to state B! $data")
                }
                // Ожидаем ответа пользователя
                handle {
                    val data: UserState = receive()
                    bot.reply(message, "And this is the response! $data")
                }
            }
        }
    }

    bot.longPolling {
        val events = messagesFlow
            .mapNotNull { update -> update.data as? PrivateContentMessage<*> }
            .map { message -> MessageEvent(bot, message) }

        launch { controller.proceed(events) }
    }
}
