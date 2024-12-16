package com.amirmousavi.post_data.mapper

import com.amirmousavi.core.domain.model.CityEntity
import com.amirmousavi.core.domain.model.PostEntity
import com.amirmousavi.post_data.remote.dto.CitiesListDTO
import com.amirmousavi.post_data.remote.dto.CityDTO
import com.amirmousavi.post_data.remote.dto.PostListDTO

fun CitiesListDTO.asListOfCityEntity(): List<CityEntity> =
    cities.map {
        it.asCityEntity()
    }

fun CityDTO.asCityEntity(): CityEntity = CityEntity(id = id, name = name)


fun PostListDTO.asListOfPostEntity(): List<PostEntity> =
    postWidgetDTOList.map {
        it.asPostEntity()
    }

fun PostListDTO.PostWidgetDTO.asPostEntity(): PostEntity = PostEntity(
    widgetType = widgetType,
    text = widgetDataDTO.text,
    title = widgetDataDTO.title,
    token = widgetDataDTO.token,
    price = widgetDataDTO.price,
    thumbnail = widgetDataDTO.thumbnail,
    district = widgetDataDTO.district
)
