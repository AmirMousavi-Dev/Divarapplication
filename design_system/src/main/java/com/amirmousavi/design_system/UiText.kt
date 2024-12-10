package com.amirmousavi.design_system

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

sealed class UiText {

    data class DynamicString(val text: String) : UiText()
    data class StringResource(val resId: Int) : UiText()


    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> text
            is StringResource -> LocalContext.current.getString(resId)
        }
    }


}