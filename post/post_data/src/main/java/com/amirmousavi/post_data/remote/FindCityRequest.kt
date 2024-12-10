package com.amirmousavi.post_data.remote

import com.google.gson.annotations.SerializedName

data class FindCityRequest(
    @SerializedName("lat")
    val latitude :Double,
    @SerializedName("long")
    val longitude :Double
)