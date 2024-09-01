package me.y9san9.ksm.ktgbotapi.state

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import kotlinx.serialization.json.Json
import me.y9san9.ksm.state.StateData
import me.y9san9.ksm.state.stateDataOf
import me.y9san9.ksm.events.EventsPlugin
import me.y9san9.ksm.json.receive
import me.y9san9.ksm.json.stateDataOf
import me.y9san9.ksm.ktgbotapi.event.MessageEvent
import me.y9san9.ksm.state.*
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.with

public class StateContext(public val context: PipelineContext)

public val StateContext.bot: TelegramBot get() = (context.require(EventsPlugin.Event) as MessageEvent).bot
public val StateContext.message: PrivateContentMessage<*> get() = (context.require(EventsPlugin.Event) as MessageEvent).message

public inline fun <reified T> StateContext.receive(json: Json = Json): T {
    return context.require(StatePlugin.StateData).receive(json)
}

public suspend inline fun StateContext.stay(): Nothing {
    val goto = context[StatePlugin.Goto] ?: error("You can't use goto inside transitions")
    goto.goto(PipelineContext.Empty)
}

public suspend inline fun <reified T> StateContext.goto(
    name: String,
    data: T
): Nothing {
    goto(name, stateDataOf(data))
}

public suspend fun StateContext.goto(
    name: String,
    data: StateData = StateData.Map.Empty
): Nothing {
    val goto = context[StatePlugin.Goto] ?: error("You can't use goto inside transitions")
    val descriptor = StateDescriptor(
        name = StateName(name),
        parameters = stateDataOf(StateRestore.STATE_DATA to data)
    )
    goto.goto(PipelineContext.with(StatePlugin.StateDescriptor, descriptor))
}
