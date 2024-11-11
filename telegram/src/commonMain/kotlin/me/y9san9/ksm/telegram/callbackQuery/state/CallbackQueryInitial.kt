package me.y9san9.ksm.telegram.callbackQuery.state

import me.y9san9.pipeline.context.PipelineContext

public fun interface CallbackQueryInitial {
    public suspend fun run(context: PipelineContext)

    public class Scope(public val context: PipelineContext)
}
