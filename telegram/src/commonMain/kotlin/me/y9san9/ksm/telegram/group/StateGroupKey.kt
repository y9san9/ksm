package me.y9san9.ksm.telegram.group

import dev.inmo.tgbotapi.types.update.abstracts.Update
import me.y9san9.ksm.telegram.group.base.StateGroupBase
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.set

public fun interface StateGroupKey {
    public suspend fun key(update: Update): Any?
}

public val StateGroup.key: StateGroupKey?
    get() = context[StateGroupBase.Config.Key]

@PipelineDsl
public fun StateGroup.Builder.key(block: (Update) -> Any?) {
    context[StateGroupBase.Config.Key] = StateGroupKey(block)
}
