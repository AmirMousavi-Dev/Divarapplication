package com.amirmousavi.post_domain.repository

import com.amirmousavi.core.domain.model.CityEntity
import com.amirmousavi.core.util.DataError
import com.amirmousavi.core.util.Result
import kotlinx.coroutines.flow.Flow

interface CityRepository {

    suspend fun syncCities()

    fun getCities(): Flow<List<CityEntity>>

    suspend fun findCity(latitude:Double,longitude:Double) :Result<CityEntity, DataError>
}