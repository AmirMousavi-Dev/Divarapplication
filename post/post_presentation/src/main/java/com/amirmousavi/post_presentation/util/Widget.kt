package com.amirmousavi.post_presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.amirmousavi.post_presentation.model.WidgetUiModel

interface Widget {

    @Composable
    fun Render(widgetUiModel: WidgetUiModel, modifier: Modifier, onClick: () -> Unit = {})
}