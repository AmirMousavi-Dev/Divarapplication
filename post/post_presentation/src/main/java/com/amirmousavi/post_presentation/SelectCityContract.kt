package com.amirmousavi.post_presentation

import com.amirmousavi.core.domain.model.CityEntity

interface SelectCityContract {

    data class State(
        val cities: List<CityEntity> = emptyList(),

    )

    sealed interface Event {
        data object GetCities : Event

    }
}