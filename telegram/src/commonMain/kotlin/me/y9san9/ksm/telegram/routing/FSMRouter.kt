package me.y9san9.ksm.telegram.routing

import me.y9san9.pipeline.context.PipelineContext

// todo: implement separate RoutePipeline and add extensions:
//  - fsm.router.goto('groupName', StateDescriptor)
//  - fsm.router.privateMessage.goto(groupName: String?, UserId, StateDescriptor)
//  - fsm.router.callbackQuery.goto(groupName: String, ChatId, MessageId, StateDescriptor)
public class FSMRouter(public val context: PipelineContext) {

}
