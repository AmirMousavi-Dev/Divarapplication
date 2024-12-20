package com.amirmousavi.post_domain.model

class PostDetailEntity(
    val contactButtonText: String,
    val enableContact: Boolean,
    val widgets: List<WidgetEntity>
) {
    data class WidgetEntity(
        val widgetType: String,
        val widgetDataEntity: WidgetDataEntity

    ) {
        data class WidgetDataEntity(
            val imageUrl: String? = null,
            val items: List<Item>? = null,
            val showThumbnail: Boolean? = null,
            val subtitle: String? = null,
            val text: String? = null,
            val title: String? = null,
            val value: String? = null,
        ) {
            data class Item(
                val imageUrl: String? = null,
                val alt: String? = null,
                val thumbnailUrl: String? = null,
            )
        }

    }
}