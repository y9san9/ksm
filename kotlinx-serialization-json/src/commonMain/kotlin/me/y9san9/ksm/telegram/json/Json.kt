package me.y9san9.ksm.telegram.json

import kotlinx.serialization.json.Json
import me.y9san9.ksm.telegram.TelegramFSM
import me.y9san9.ksm.telegram.json.base.JsonPlugin
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.setSubject
import me.y9san9.pipeline.subject

public var TelegramFSM.Builder.json: Json
    get() = context.subject.require(JsonPlugin.Subject.Json)
    set(value) { context.setSubject(JsonPlugin.Subject.Json, value) }
