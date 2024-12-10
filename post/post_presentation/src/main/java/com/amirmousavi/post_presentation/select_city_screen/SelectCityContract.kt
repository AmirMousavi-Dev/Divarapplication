package com.amirmousavi.post_presentation.select_city_screen

import com.amirmousavi.core.domain.model.CityEntity

interface SelectCityContract {

    data class State(
        val cities: List<CityEntity> = emptyList(),
        val permissionDialogQueue : List<String> = emptyList()

    )

    sealed interface Event {
        data object GetCities : Event

        data class OnPermissionResult(val isGranted :Boolean , val permission :String) : Event
        data object OnPermissionDialogDismiss : Event

    }
}