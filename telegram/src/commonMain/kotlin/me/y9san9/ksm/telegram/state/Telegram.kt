package me.y9san9.ksm.telegram.state

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.update.abstracts.Update
import me.y9san9.ksm.telegram.handler.base.TelegramUpdateHandlerBase.Subject
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.require

@PipelineDsl
public val UpdateHandler.Scope.bot: TelegramBot
    get() = context.require(Subject.Bot)

@PipelineDsl
public val UpdateHandler.Scope.update: Update
    get() = context.require(Subject.Update)
