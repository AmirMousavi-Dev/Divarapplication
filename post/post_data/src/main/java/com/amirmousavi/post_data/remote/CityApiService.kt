package com.amirmousavi.post_data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface CityApiService {

    @GET("api/v1/place/list")
    @Headers("x-access-token: Basic YXBpa2V5OjY5Y1dxVW8wNGhpNFdMdUdBT2IzMmRXZXQjsllsVzBtSkNiwU9yLUxEamNDUXFMSzJnR29mS3plZg==")
    suspend fun getCities(): Response<CitiesListDTO>


    @POST("api/v1/place/find")
    @Headers("x-access-token: Basic YXBpa2V5OjY5Y1dxVW8wNGhpNFdMdUdBT2IzMmRXZXQjsllsVzBtSkNiwU9yLUxEamNDUXFMSzJnR29mS3plZg==")
    suspend fun findCity(
        @Body findCityRequest: FindCityRequest
    ): CityDTO


}