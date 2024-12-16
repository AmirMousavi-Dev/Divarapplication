package com.amirmousavi.post_data.repository

import com.amirmousavi.core.data.database.CityDao
import com.amirmousavi.core.domain.model.CityEntity
import com.amirmousavi.core.util.BaseApiResponse
import com.amirmousavi.core.util.DataError
import com.amirmousavi.core.util.Result
import com.amirmousavi.post_data.mapper.asCityEntity
import com.amirmousavi.post_data.mapper.asListOfCityEntity
import com.amirmousavi.post_data.model.FindCityRequest
import com.amirmousavi.post_data.remote.CityApiService
import com.amirmousavi.post_domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow

class CityRepositoryImpl(
    private val dao: CityDao,
    private val apiService: CityApiService
) : CityRepository, BaseApiResponse() {


    override suspend fun syncCities() {
        try {
            val response = apiService.getCities()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let { citiesDTO ->
                    dao.insertAll(citiesDTO.asListOfCityEntity())
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun getCities(): Flow<List<CityEntity>> = dao.getAllCities()


    override suspend fun findCity(
        latitude: Double,
        longitude: Double
    ): Result<CityEntity, DataError> = safeApiCall {
        apiService.findCity(
            findCityRequest = FindCityRequest(
                latitude = latitude,
                longitude = longitude
            )
        ).asCityEntity()
    }


}

