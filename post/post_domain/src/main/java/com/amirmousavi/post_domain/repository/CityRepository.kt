package com.amirmousavi.post_domain.repository

import androidx.paging.Pager
import com.amirmousavi.core.domain.model.CityEntity
import kotlinx.coroutines.flow.Flow

interface CityRepository {

    suspend fun syncCities()

    fun getCities(): Flow<List<CityEntity>>
}