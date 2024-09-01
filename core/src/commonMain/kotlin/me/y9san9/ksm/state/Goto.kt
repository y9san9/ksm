package me.y9san9.ksm.state

import me.y9san9.pipeline.context.PipelineContext

// todo: make GotoPlugin; Make NavigationPlugin and the difference between goto and navigation
//  is that navigation will allow to navigate to state without finishing current state
//  Maybe goto should even be plugin that is dependent on Navigation
public fun interface Goto {
    public suspend fun goto(context: PipelineContext): Nothing
}
