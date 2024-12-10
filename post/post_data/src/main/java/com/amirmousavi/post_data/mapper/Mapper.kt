package com.amirmousavi.post_data.mapper

import com.amirmousavi.core.domain.model.CityEntity
import com.amirmousavi.post_data.remote.CitiesDTO

fun CitiesDTO.asListOfCityEntity(): List<CityEntity> =
    cities.map {
        CityEntity(
            id = it.id,
            name = it.name
        )
    }