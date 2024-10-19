package me.y9san9.ksm.telegram

import me.y9san9.ksm.routing.StateRouting
import me.y9san9.ksm.routing.buildStateList
import me.y9san9.ksm.telegram.plugin.TelegramFSMBase.Subject
import me.y9san9.pipeline.subject.setSubject
import me.y9san9.pipeline.subject.subject

public inline fun TelegramFSM.Builder.routing(
    block: StateRouting.() -> Unit
) {
    context.setSubject(
        element = Subject.StateList,
        value = buildStateList(context.subject[Subject.StateList], block)
    )
}
