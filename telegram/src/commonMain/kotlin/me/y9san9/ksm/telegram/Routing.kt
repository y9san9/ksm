package me.y9san9.ksm.telegram

import me.y9san9.ksm.telegram.base.TelegramFSMBase.Subject
import me.y9san9.ksm.telegram.routing.StateRouting
import me.y9san9.ksm.telegram.routing.buildStateList
import me.y9san9.pipeline.setSubject
import me.y9san9.pipeline.subject

public inline fun TelegramFSM.Builder.routing(
    block: StateRouting.() -> Unit
) {
    val states = buildStateList(context.subject[Subject.StateList], block)
    context.setSubject(Subject.StateList, states)
}
