package me.y9san9.ksm.telegram.callbackQuery.state

import dev.inmo.tgbotapi.extensions.utils.extensions.raw.inline_message_id
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.message
import dev.inmo.tgbotapi.types.InlineMessageId
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.MessageContent
import dev.inmo.tgbotapi.types.update.CallbackQueryUpdate
import dev.inmo.tgbotapi.utils.RiskFeature
import me.y9san9.ksm.telegram.state.update
import me.y9san9.pipeline.annotation.PipelineDsl

@PipelineDsl
public val CallbackQueryHandler.Scope.callbackQueryUpdate: CallbackQueryUpdate
    get() = update as CallbackQueryUpdate

/**
 * If [message] is null, [inlineMessageId] is not null
 */
@OptIn(RiskFeature::class)
@PipelineDsl
public val CallbackQueryHandler.Scope.message: ContentMessage<MessageContent>?
    get() = callbackQueryUpdate.data.message

/**
 * If [inlineMessageId] is null, [message] is not null
 */
@OptIn(RiskFeature::class)
@PipelineDsl
public val CallbackQueryHandler.Scope.inlineMessageId: InlineMessageId?
    get() = callbackQueryUpdate.data.inline_message_id

@PipelineDsl
public val CallbackQueryHandler.Scope.user: User
    get() = callbackQueryUpdate.data.from
