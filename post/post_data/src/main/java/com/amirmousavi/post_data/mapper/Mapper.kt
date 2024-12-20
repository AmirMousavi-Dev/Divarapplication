package com.amirmousavi.post_data.mapper

import com.amirmousavi.core.data.model.PostViewDbEntity
import com.amirmousavi.core.domain.model.CityEntity
import com.amirmousavi.core.domain.model.PostEntity
import com.amirmousavi.post_data.remote.dto.CitiesListDTO
import com.amirmousavi.post_data.remote.dto.CityDTO
import com.amirmousavi.post_data.remote.dto.PostListDTO
import com.amirmousavi.post_data.remote.dto.PostViewDTO
import com.amirmousavi.post_domain.model.PostDetailEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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

fun PostViewDTO.asPostViewDbEntity(
    token: String,
    gson: Gson
): PostViewDbEntity = PostViewDbEntity(
    token = token,
    contactButtonText = contactButtonText,
    enableContact = enableContact,
    widgets = gson.toJson(widgets)
)

fun PostViewDTO.asPostDetailEntity(
): PostDetailEntity = PostDetailEntity(
    contactButtonText = contactButtonText,
    enableContact = enableContact,
    widgets = widgets?.map { widget ->
        PostDetailEntity.WidgetEntity(
            widgetType = widget.widgetType ?: "",
            widgetDataEntity = PostDetailEntity.WidgetEntity.WidgetDataEntity(
                imageUrl = widget.widgetDataDTO?.imageUrl,
                items = widget.widgetDataDTO?.items?.map { item ->
                    PostDetailEntity.WidgetEntity.WidgetDataEntity.Item(
                        imageUrl = item.image?.imageUrl,
                        alt = item.image?.alt,
                        thumbnailUrl = item.image?.thumbnailUrl
                    )
                },
                showThumbnail = widget.widgetDataDTO?.showThumbnail,
                subtitle = widget.widgetDataDTO?.subtitle,
                text = widget.widgetDataDTO?.text,
                title = widget.widgetDataDTO?.title,
                value = widget.widgetDataDTO?.value
            )
        )
    } ?: emptyList()
)


fun PostViewDbEntity.asPostViewDTO(
    gson: Gson
): PostViewDTO {
    val listType = object : TypeToken<List<PostViewDTO.Widget>>() {}.type
    val widgets: List<PostViewDTO.Widget> = gson.fromJson(widgets, listType)
    return PostViewDTO(
        enableContact = enableContact,
        contactButtonText = contactButtonText,
        widgets = widgets
    )
}


fun PostViewDbEntity.asPostDetailEntity(gson: Gson): PostDetailEntity {
    return this.asPostViewDTO(gson).asPostDetailEntity()
}
