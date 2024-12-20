package com.amirmousavi.post_data.remote.dto


import com.google.gson.annotations.SerializedName

data class PostViewDTO(
    @SerializedName("contact_button_text")
    val contactButtonText: String,
    @SerializedName("enable_contact")
    val enableContact: Boolean,
    @SerializedName("widgets")
    val widgets: List<Widget>? = null
) {
    data class Widget(
        @SerializedName("data")
        val widgetDataDTO: WidgetDataDTO? = null,
        @SerializedName("widget_type")
        val widgetType: String? = null
    ) {
        data class WidgetDataDTO(
            @SerializedName("image_url")
            val imageUrl: String? = null,
            @SerializedName("items")
            val items: List<Item>? = null,
            @SerializedName("show_thumbnail")
            val showThumbnail: Boolean? = null,
            @SerializedName("subtitle")
            val subtitle: String? = null,
            @SerializedName("text")
            val text: String? = null,
            @SerializedName("title")
            val title: String? = null,
            @SerializedName("value")
            val value: String? = null,
        ) {
            data class Item(
                @SerializedName("image")
                val image: Image? = null,
            ) {
                data class Image(
                    @SerializedName("url")
                    val imageUrl: String? = null,
                    @SerializedName("alt")
                    val alt: String? = null,
                    @SerializedName("thumbnail_url")
                    val thumbnailUrl: String? = null,
                )
            }
        }
    }
}