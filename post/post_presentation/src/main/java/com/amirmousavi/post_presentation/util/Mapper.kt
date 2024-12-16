package com.amirmousavi.post_presentation.util

import com.amirmousavi.core.domain.model.PostEntity
import com.amirmousavi.post_presentation.model.PostRowUiModel

fun PostEntity.asPostRowUiModel() :PostRowUiModel = PostRowUiModel(
    title = title,
    token = token,
    price = price,
    thumbnail = thumbnail,
    district = district
)