package com.amirmousavi.post_presentation.util

import com.amirmousavi.post_presentation.post_list_screen.component.PostRowWidget
import com.amirmousavi.post_presentation.post_list_screen.component.SubTitleRowWidget
import com.amirmousavi.post_presentation.post_list_screen.component.TitleRowWidget

object PostWidgetFactory {

    fun createWidget(type: String): Widget {
        return when (type) {
           "TITLE_ROW"-> TitleRowWidget()
           "SUBTITLE_ROW"-> SubTitleRowWidget()
           "POST_ROW"-> PostRowWidget()
            else -> throw IllegalArgumentException("Invalid widget type: $type")
        }
    }
}