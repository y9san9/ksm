package me.y9san9.ksm.telegram.privateMessage

import me.y9san9.ksm.telegram.FlowSetupPhase
import me.y9san9.ksm.telegram.TelegramFSM
import me.y9san9.ksm.telegram.flow.TelegramFlow
import me.y9san9.ksm.telegram.flow.set
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.insertPhaseAfter
import me.y9san9.pipeline.plugin.PipelinePlugin
import me.y9san9.pipeline.set

public class PrivateMessageFlow : PipelinePlugin {
    override val name: String = "PrivateMessageFlow"

    override fun apply(context: MutablePipelineContext) {
        context.set(TelegramFSM.Pipeline) {
            insertPhaseAfter(FlowSetupPhase) {
                runnable {
                    this.context.set(TelegramFlow) {

                    }
                }
            }
        }
    }
}
