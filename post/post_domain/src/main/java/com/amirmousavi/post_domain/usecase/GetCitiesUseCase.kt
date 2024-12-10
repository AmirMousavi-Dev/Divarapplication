package com.amirmousavi.post_domain.usecase

import com.amirmousavi.core.domain.model.CityEntity
import com.amirmousavi.post_domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    private val repository: CityRepository
) {

    suspend operator fun invoke(
    ): Flow<List<CityEntity>> {
        repository.syncCities()
        return repository.getCities()
    }
}