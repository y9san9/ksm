package me.y9san9.ksm.telegram

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import me.y9san9.ksm.state.StateHandler
import me.y9san9.ksm.state.StateTransition
import me.y9san9.ksm.telegram.handler.plugin.TelegramHandlerBase
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.subject.subject

public val StateHandler.Scope.bot: TelegramBot
    get() = context.subject.require(TelegramHandlerBase.Subject.Bot)

public val StateTransition.Scope.bot: TelegramBot
    get() = context.subject.require(TelegramHandlerBase.Subject.Bot)

public val StateHandler.Scope.message: PrivateContentMessage<*>
    get() = context.subject.require(TelegramHandlerBase.Subject.Update).data as PrivateContentMessage<*>

public val StateTransition.Scope.message: PrivateContentMessage<*>
    get() = context.subject.require(TelegramHandlerBase.Subject.Update).data as PrivateContentMessage<*>
