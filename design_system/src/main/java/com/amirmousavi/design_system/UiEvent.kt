package com.amirmousavi.design_system

sealed class UiEvent {
    data object Success : UiEvent()
    data class ShowMessage(val message: UiText) : UiEvent()
}