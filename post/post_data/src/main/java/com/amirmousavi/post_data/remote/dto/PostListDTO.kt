package com.amirmousavi.post_data.remote.dto


import com.google.gson.annotations.SerializedName

data class PostListDTO(
    @SerializedName("last_post_date")
    val lastPostDate: Long,
    @SerializedName("widget_list")
    val postWidgetDTOList: List<PostWidgetDTO>
) {
    data class PostWidgetDTO(
        @SerializedName("data")
        val widgetDataDTO: WidgetDataDTO,
        @SerializedName("widget_type")
        val widgetType: String
    ) {
        data class WidgetDataDTO(
            @SerializedName("district")
            val district: String,
            @SerializedName("price")
            val price: String,
            @SerializedName("text")
            val text: String,
            @SerializedName("thumbnail")
            val thumbnail: String,
            @SerializedName("title")
            val title: String,
            @SerializedName("token")
            val token: String
        )
    }
}