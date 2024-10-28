package me.y9san9.ksm.telegram.callbackQuery.state

import dev.inmo.tgbotapi.types.buttons.InlineKeyboardButtons.CallbackDataInlineKeyboardButton
import kotlin.reflect.KProperty

public data class CallbackData(val string: String) {
    public fun button(text: String): CallbackDataInlineKeyboardButton {
        return CallbackDataInlineKeyboardButton(text = text, callbackData = string)
    }

    public companion object {
        public operator fun provideDelegate(
            thisRef: Any?,
            property: KProperty<*>
        ): Lazy<CallbackData> {
            return lazy { CallbackData(property.name) }
        }
    }
}
