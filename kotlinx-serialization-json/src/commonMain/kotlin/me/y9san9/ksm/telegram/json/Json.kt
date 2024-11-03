package me.y9san9.ksm.telegram.json

import kotlinx.serialization.json.Json
import me.y9san9.ksm.telegram.TelegramFSM
import me.y9san9.ksm.telegram.base.TelegramFSMBase.Pipeline
import me.y9san9.ksm.telegram.json.base.JsonPlugin
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.build
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.setSubject
import me.y9san9.pipeline.subject

@PipelineDsl
public var TelegramFSM.Builder.json: Json
    get() = context.require(Pipeline).subject.require(JsonPlugin.Json)
    set(value) {
        context[Pipeline] = context[Pipeline].build {
            setSubject(JsonPlugin.Json, value)
        }
    }
