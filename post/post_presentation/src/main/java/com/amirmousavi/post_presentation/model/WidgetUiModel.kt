package com.amirmousavi.post_presentation.model

class WidgetUiModel(
    val id: Int? = null,
    val title: String? = null,
    val token: String? = null,
    val price: String? = null,
    val thumbnail: String? = null,
    val district: String? = null,
    val imageUrl: String? = null,
    val items: List<Item>? = null,
    val showThumbnail: Boolean? = null,
    val subtitle: String? = null,
    val text: String? = null,
    val value: String? = null,
) {
    data class Item(
        val imageUrl: String? = null,
        val alt: String? = null,
        val thumbnailUrl: String? = null,
    )
}