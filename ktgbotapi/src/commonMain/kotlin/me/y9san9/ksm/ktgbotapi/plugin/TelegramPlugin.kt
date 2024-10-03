package me.y9san9.ksm.ktgbotapi.plugin

import me.y9san9.ksm.state.StateData
import me.y9san9.ksm.events.EventsPlugin
import me.y9san9.ksm.json.JsonPlugin
import me.y9san9.ksm.json.JsonDecodePhase
import me.y9san9.ksm.json.JsonEncodePhase
import me.y9san9.ksm.ktgbotapi.start.StartCommandPlugin
import me.y9san9.ksm.ktgbotapi.storage.TelegramStorage
import me.y9san9.ksm.logger.withLogger
import me.y9san9.ksm.restore.RestorePlugin
import me.y9san9.ksm.setup.SetupPlugin
import me.y9san9.ksm.state.StatePlugin
import me.y9san9.pipeline.PipelineBuilder
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline._PipelineRunnable

public object TelegramPlugin : PipelinePlugin {
    override fun apply(builder: PipelineBuilder) {
        with(builder) {
            withLogger()

            install(EventsPlugin)

            context.withPipeline(EventsPlugin.KeyPipeline) {
                insertPhaseAfter(_PipelineRunnable.Start.name, TelegramKeyPhase)
            }

            context.withPipeline(EventsPlugin.Pipeline) {
                install(StatePlugin)
                install(StartCommandPlugin)
                install(JsonPlugin)

                context.withPipeline(RestorePlugin.RestorePipeline) {
                    insertPhaseBefore(JsonDecodePhase.name, TelegramRestorePhase)
                }
                context.withPipeline(RestorePlugin.SavePipeline) {
                    insertPhaseAfter(JsonEncodePhase.name, TelegramSavePhase)
                }
                context.withPipeline(SetupPlugin.Pipeline) {
                    insertPhaseAfter(_PipelineRunnable.Start.name, TelegramSetupPhase)
                }
            }
        }
    }

    public object Data : PipelineElement<StateData>
    public object Storage : PipelineElement<TelegramStorage>
    public object MainPipeline : PipelineElement<Pipeline>
}
