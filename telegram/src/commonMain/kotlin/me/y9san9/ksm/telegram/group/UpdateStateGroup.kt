package me.y9san9.ksm.telegram.group

import me.y9san9.aqueue.AQueue
import me.y9san9.ksm.telegram.TelegramFSM
import me.y9san9.ksm.telegram.base.TelegramFSMBase
import me.y9san9.ksm.telegram.group.base.UpdateStateGroupBase
import me.y9san9.ksm.telegram.group.base.UpdateStateGroupBase.Config
import me.y9san9.ksm.telegram.state.routing.UpdateStateList
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.plugin.install
import me.y9san9.pipeline.setSubject
import me.y9san9.pipeline.subject

public class UpdateStateGroup(public val context: PipelineContext) {

    @PipelineDsl
    public class Builder {
        public val context: MutablePipelineContext = mutablePipelineContextOf()

        init {
            context.install(UpdateStateGroupBase)
        }

        public var aqueue: AQueue?
            get() = context[Config.AQueue]
            set(value) { context[Config.AQueue] = value }

        public var key: UpdateKey?
            get() = context[Config.Key]
            set(value) { context[Config.Key] = value }

        public var filter: UpdateFilter?
            get() = context[Config.Filter]
            set(value) { context[Config.Filter] = value }

        public var storage: UpdateStorage?
            get() = context[Config.Storage]
            set(value) { context[Config.Storage] = value }

        public var stateList: UpdateStateList?
            get() = context[Config.StateList]
            set(value) { context[Config.StateList] = value }

        public fun build(): UpdateStateGroup {
            return UpdateStateGroup(context.toPipelineContext())
        }
    }
}

public fun TelegramFSM.Builder.addUpdateStateGroup(group: UpdateStateGroup) {
    val groups = context.subject.require(TelegramFSMBase.Subject.StateGroups)
    context.setSubject(TelegramFSMBase.Subject.StateGroups, value = groups + group)
}
