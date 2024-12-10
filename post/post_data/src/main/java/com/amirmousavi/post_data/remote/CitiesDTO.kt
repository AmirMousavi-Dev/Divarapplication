package com.amirmousavi.post_data.remote


import com.google.gson.annotations.SerializedName

data class CitiesDTO(
    @SerializedName("cities")
    val cities: List<City>
) {
    data class City(
        @SerializedName("centroid")
        val centroid: Centroid,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("radius")
        val radius: Int,
        @SerializedName("slug")
        val slug: String
    ) {
        data class Centroid(
            @SerializedName("latitude")
            val latitude: Double,
            @SerializedName("longitude")
            val longitude: Double
        )
    }
}