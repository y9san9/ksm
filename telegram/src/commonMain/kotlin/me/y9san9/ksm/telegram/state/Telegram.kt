package me.y9san9.ksm.telegram.state

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.utils.asFromUser
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.utils.PreviewFeature
import me.y9san9.ksm.telegram.handler.base.TelegramUpdateHandlerBase
import me.y9san9.pipeline.context.require

public val StateHandler.Scope.bot: TelegramBot
    get() = context.require(TelegramUpdateHandlerBase.Subject.Bot)

public val StateHandler.Scope.message: PrivateContentMessage<*>
    get() = context.require(TelegramUpdateHandlerBase.Subject.Update).data as PrivateContentMessage<*>

@OptIn(PreviewFeature::class)
public val StateHandler.Scope.user: User
    get() = context.require(TelegramUpdateHandlerBase.Subject.Update).data.asFromUser()?.user
        ?: error("User is not available")
