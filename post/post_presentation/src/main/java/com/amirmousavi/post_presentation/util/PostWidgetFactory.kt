package com.amirmousavi.post_presentation.util

import com.amirmousavi.post_presentation.post_detail_screen.component.DescriptionRowWidget
import com.amirmousavi.post_presentation.post_detail_screen.component.HeaderRowWidget
import com.amirmousavi.post_presentation.post_detail_screen.component.ImageSliderRowWidget
import com.amirmousavi.post_presentation.post_detail_screen.component.InfoRowWidget
import com.amirmousavi.post_presentation.post_list_screen.component.PostRowWidget
import com.amirmousavi.post_presentation.post_list_screen.component.SubTitleRowWidget
import com.amirmousavi.post_presentation.post_list_screen.component.TitleRowWidget

object PostWidgetFactory {

    fun createWidget(type: String): Widget {
        return when (type) {
            "TITLE_ROW" -> TitleRowWidget()
            "SUBTITLE_ROW" -> SubTitleRowWidget()
            "POST_ROW" -> PostRowWidget()
            "HEADER_ROW" -> HeaderRowWidget()
            "DESCRIPTION_ROW" -> DescriptionRowWidget()
            "IMAGE_SLIDER_ROW" -> ImageSliderRowWidget()
            "INFO_ROW" -> InfoRowWidget()
            else -> throw IllegalArgumentException("Invalid widget type: $type")
        }
    }
}