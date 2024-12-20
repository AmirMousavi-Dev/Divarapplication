package com.amirmousavi.post_data.remote

import com.amirmousavi.post_data.model.FindCityRequest
import com.amirmousavi.post_data.remote.dto.CitiesListDTO
import com.amirmousavi.post_data.remote.dto.CityDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CityApiService {

    @GET("api/v1/place/list")
    suspend fun getCities(): Response<CitiesListDTO>


    @POST("api/v1/place/find")
    suspend fun findCity(
        @Body findCityRequest: FindCityRequest
    ): CityDTO


}