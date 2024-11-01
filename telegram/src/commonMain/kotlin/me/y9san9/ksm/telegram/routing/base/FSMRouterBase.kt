package me.y9san9.ksm.telegram.routing.base

import dev.inmo.tgbotapi.bot.TelegramBot
import me.y9san9.ksm.telegram.group.UpdateStorage
import me.y9san9.ksm.telegram.state.routing.GotoCommand
import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.buildPipeline
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.insertPhaseLast
import me.y9san9.pipeline.plugin.PipelinePlugin

public object FSMRouterBase : PipelinePlugin {
    override val name: String = "FSMRouterBase"

    override fun apply(context: MutablePipelineContext) {
        context[Config.Pipeline] = Config.Pipeline.Default
    }

    public object Config {
        public object Pipeline : PipelineElement<me.y9san9.pipeline.Pipeline> {
            public val Default: me.y9san9.pipeline.Pipeline = buildPipeline {
                insertPhaseLast(GotoLooperPhase)
                insertPhaseLast(SavePhase)
            }
        }
    }

    public object Subject {
        public object Storage : PipelineElement<UpdateStorage>
        public object Bot : PipelineElement<TelegramBot>

        public object GotoPipeline : PipelineElement<Pipeline> {
            public val Default: Pipeline = buildPipeline {
                insertPhaseLast(GotoRoutePhase)
                insertPhaseLast(GotoRunPhase)
            }
        }
        public object GotoCommand : PipelineElement<me.y9san9.ksm.telegram.state.routing.GotoCommand>
    }
}
