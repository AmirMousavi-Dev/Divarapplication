package com.amirmousavi.post_domain.usecase

import com.amirmousavi.core.domain.datastore.DivarDataStore
import com.amirmousavi.core.domain.model.CityEntity
import com.amirmousavi.core.util.DataError
import com.amirmousavi.core.util.Result
import com.amirmousavi.post_domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FindCityUseCase @Inject constructor(
    private val repository: CityRepository,
    private val dataStore: DivarDataStore,
) {

    operator fun invoke(
        latitude :Double,
        longitude:Double
    ): Flow<Result<CityEntity,DataError>> = flow {
        val result = repository.findCity(latitude = latitude, longitude = longitude)
        if (result is Result.Success) {
            dataStore.saveCityId(result.data.id)
            dataStore.saveCityFaName(result.data.name)
        }
        emit(result)
    }


}