package me.y9san9.ksm.telegram.group

import me.y9san9.ksm.telegram.TelegramFSM
import me.y9san9.ksm.telegram.base.TelegramFSMBase
import me.y9san9.ksm.telegram.group.base.UpdateGroupBase
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.plugin.install
import me.y9san9.pipeline.setSubject
import me.y9san9.pipeline.subject

public class UpdateGroup(public val context: PipelineContext) {

    @PipelineDsl
    public open class Builder {
        public val context: MutablePipelineContext = mutablePipelineContextOf()

        init {
            context.install(UpdateGroupBase)
        }

        public fun build(): UpdateGroup {
            return UpdateGroup(context.toPipelineContext())
        }
    }
}

public fun TelegramFSM.Builder.addUpdateGroup(group: UpdateGroup) {
    val groups = context.subject.require(TelegramFSMBase.Subject.StateGroups)
    context.setSubject(TelegramFSMBase.Subject.StateGroups, value = groups + group)
}