package me.y9san9.ksm.ktgbotapi.event

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import me.y9san9.pipeline.context.PipelineElement

public class MessageEvent(
    public val bot: TelegramBot,
    public val message: PrivateContentMessage<*>
) {
    public companion object : PipelineElement<MessageEvent>
}
