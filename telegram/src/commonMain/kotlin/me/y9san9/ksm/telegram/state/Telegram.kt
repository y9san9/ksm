package me.y9san9.ksm.telegram.state

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import me.y9san9.ksm.telegram.handler.base.TelegramUpdateHandlerBase
import me.y9san9.pipeline.context.require

public val StateHandler.Scope.bot: TelegramBot
    get() = context.require(TelegramUpdateHandlerBase.Subject.Bot)

public val StateTransition.Scope.bot: TelegramBot
    get() = context.require(TelegramUpdateHandlerBase.Subject.Bot)

public val StateHandler.Scope.message: PrivateContentMessage<*>
    get() = context.require(TelegramUpdateHandlerBase.Subject.Update).data as PrivateContentMessage<*>

public val StateTransition.Scope.message: PrivateContentMessage<*>
    get() = context.require(TelegramUpdateHandlerBase.Subject.Update).data as PrivateContentMessage<*>
