package me.y9san9.ksm.telegram.group

import me.y9san9.aqueue.AQueue
import me.y9san9.ksm.telegram.TelegramFSM
import me.y9san9.ksm.telegram.base.TelegramFSMBase.Pipeline
import me.y9san9.ksm.telegram.base.TelegramFSMBase.StateGroups
import me.y9san9.ksm.telegram.group.base.UpdateStateGroupBase
import me.y9san9.ksm.telegram.group.base.UpdateStateGroupBase.AQueue
import me.y9san9.ksm.telegram.group.base.UpdateStateGroupBase.Filter
import me.y9san9.ksm.telegram.group.base.UpdateStateGroupBase.Key
import me.y9san9.ksm.telegram.group.base.UpdateStateGroupBase.StateList
import me.y9san9.ksm.telegram.group.base.UpdateStateGroupBase.Storage
import me.y9san9.ksm.telegram.state.routing.UpdateStateList
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.plugin.install
import me.y9san9.pipeline.set
import me.y9san9.pipeline.setSubject
import me.y9san9.pipeline.subject

public data class UpdateStateGroup(public val context: PipelineContext) {

    @PipelineDsl
    public class Builder {
        public val context: MutablePipelineContext = mutablePipelineContextOf()

        init {
            context.install(UpdateStateGroupBase)
        }

        public var aqueue: AQueue?
            get() = context[AQueue]
            set(value) { context[AQueue] = value }

        public var key: UpdateKey?
            get() = context[Key]
            set(value) { context[Key] = value }

        public var filter: UpdateFilter?
            get() = context[Filter]
            set(value) { context[Filter] = value }

        public var storage: UpdateStorage?
            get() = context[Storage]
            set(value) { context[Storage] = value }

        public var stateList: UpdateStateList?
            get() = context[StateList]
            set(value) { context[StateList] = value }

        public fun build(): UpdateStateGroup {
            return UpdateStateGroup(context.toPipelineContext())
        }
    }
}

public fun TelegramFSM.Builder.addUpdateStateGroup(group: UpdateStateGroup) {
    context.set(Pipeline) {
        setSubject(StateGroups, value = subject.require(StateGroups) + group)
    }
}
