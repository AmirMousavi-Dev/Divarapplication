package com.amirmousavi.post_data.remote


import com.google.gson.annotations.SerializedName

data class CitiesListDTO(
    @SerializedName("cities")
    val cities: List<CityDTO>
)
