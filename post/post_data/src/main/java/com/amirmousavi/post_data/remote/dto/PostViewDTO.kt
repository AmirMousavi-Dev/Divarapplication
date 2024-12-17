package com.amirmousavi.post_data.remote.dto


import com.google.gson.annotations.SerializedName

data class PostViewDTO(
    @SerializedName("contact_button_text")
    val contactButtonText: String,
    @SerializedName("enable_contact")
    val enableContact: Boolean,
    @SerializedName("widgets")
    val widgets: List<Widget>
) {
    data class Widget(
        @SerializedName("data")
        val widgetDataDTO: WidgetDataDTO,
        @SerializedName("widget_type")
        val widgetType: String
    ) {
        data class WidgetDataDTO(
            @SerializedName("image_url")
            val imageUrl: String,
            @SerializedName("items")
            val items: List<Item>,
            @SerializedName("show_thumbnail")
            val showThumbnail: Boolean,
            @SerializedName("subtitle")
            val subtitle: String,
            @SerializedName("text")
            val text: String,
            @SerializedName("title")
            val title: String,
            @SerializedName("value")
            val value: String
        ) {
            data class Item(
                @SerializedName("image_url")
                val imageUrl: String
            )
        }
    }
}