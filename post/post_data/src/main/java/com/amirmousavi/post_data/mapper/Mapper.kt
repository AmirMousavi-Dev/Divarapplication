package com.amirmousavi.post_data.mapper

import com.amirmousavi.core.domain.model.CityEntity
import com.amirmousavi.post_data.remote.CitiesListDTO
import com.amirmousavi.post_data.remote.CityDTO

fun CitiesListDTO.asListOfCityEntity(): List<CityEntity> =
    cities.map {
        it.asCityEntity()
    }

fun CityDTO.asCityEntity(): CityEntity = CityEntity(id = id, name = name)
