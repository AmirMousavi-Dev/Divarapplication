package com.amirmousavi.post_presentation.util

import com.amirmousavi.core.domain.model.PostEntity
import com.amirmousavi.post_domain.model.PostDetailEntity
import com.amirmousavi.post_presentation.model.HeaderRowUiModel
import com.amirmousavi.post_presentation.model.ImageSliderUiModel
import com.amirmousavi.post_presentation.model.PostRowUiModel
import com.amirmousavi.post_presentation.model.WidgetUiModel

fun WidgetUiModel.asPostRowUiModel(): PostRowUiModel = PostRowUiModel(
    title = title,
    token = token,
    price = price,
    thumbnail = thumbnail,
    district = district
)


fun PostEntity.asWidgetEntity(): WidgetUiModel = WidgetUiModel(
    id = id,
    text = text,
    title = title,
    token = token,
    price = price,
    thumbnail = thumbnail,
    district = district
)


fun PostDetailEntity.WidgetEntity.WidgetDataEntity.asWidgetUiModel(): WidgetUiModel = WidgetUiModel(
    imageUrl = imageUrl,
    items = items?.map {
        WidgetUiModel.Item(
            imageUrl = it.imageUrl,
            alt = it.alt,
            thumbnailUrl = it.thumbnailUrl
        )
    },
    showThumbnail = showThumbnail,
    subtitle = subtitle,
    text = text,
    title = title,
    value = value,
)


fun WidgetUiModel.asHeaderRowUiModel(): HeaderRowUiModel = HeaderRowUiModel(
    title = title ?: "",
    subtitle = subtitle ?: "",
    imageUrl = imageUrl,
    showThumbnail = showThumbnail ?: false
)

fun WidgetUiModel.Item.asImageSliderUiModel(): ImageSliderUiModel = ImageSliderUiModel(
    url = imageUrl,
    alt = alt,
    thumbnailUrl = thumbnailUrl
)

