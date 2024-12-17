package com.amirmousavi.post_data.remote

import com.amirmousavi.post_data.model.GetCityByIdRequest
import com.amirmousavi.post_data.remote.dto.PostListDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PostApiService {

    @POST("api/v1/post/list")
    suspend fun getPostByCityId(
        @Query("city") cityId: Int,
        @Body getCityByIdRequest: GetCityByIdRequest
    ): PostListDTO


    @GET("api/v1/post/view/{token}")
    suspend fun getPostByToken(
        @Path("token") token: String,
    ): PostListDTO
}