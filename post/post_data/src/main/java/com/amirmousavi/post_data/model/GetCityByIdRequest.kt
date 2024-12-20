package com.amirmousavi.post_data.model

import com.google.gson.annotations.SerializedName

data class GetCityByIdRequest(
    @SerializedName("page")
    val page: Int,
    @SerializedName("last_post_date")
    val lastPostDate: Long
)