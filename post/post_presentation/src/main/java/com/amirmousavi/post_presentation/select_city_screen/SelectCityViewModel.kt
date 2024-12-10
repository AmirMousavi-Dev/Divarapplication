package com.amirmousavi.post_presentation.select_city_screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirmousavi.core.domain.datastore.DivarDataStore
import com.amirmousavi.core.util.Result
import com.amirmousavi.design_system.UiEvent
import com.amirmousavi.design_system.util.asUiText
import com.amirmousavi.post_domain.usecase.FindCityUseCase
import com.amirmousavi.post_domain.usecase.GetCitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectCityViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase,
    private val findCityUseCase: FindCityUseCase,
    private val dataStore: DivarDataStore
) : ViewModel() {

    private val _state = MutableStateFlow(SelectCityContract.State())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _permissionQueue = mutableStateListOf<String>()


    fun onEvent(event: SelectCityContract.Event) {
        when (event) {
            is SelectCityContract.Event.GetCities -> getCities()
            is SelectCityContract.Event.OnPermissionResult -> onPermissionResult(
                permission = event.permission,
                isGranted = event.isGranted
            )

            SelectCityContract.Event.OnPermissionDialogDismiss -> onPermissionDialogDismiss()
            is SelectCityContract.Event.SaveCityByLocation -> saveCItyByLocation(
                latitude = event.latitude,
                longitude = event.longitude
            )

            is SelectCityContract.Event.OnCitySelected -> {
                viewModelScope.launch {
                    dataStore.saveCityId(event.cityId)
                    dataStore.saveCityFaName(event.cityFaName)
                    _uiEvent.send(UiEvent.Success)
                }
            }
        }
    }


    @OptIn(FlowPreview::class)
    private fun saveCItyByLocation(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            findCityUseCase.invoke(latitude = latitude, longitude = longitude)
                .debounce(1000)
                .collect { result ->
                    when (result) {
                        is Result.Error -> {
                            _uiEvent.send(UiEvent.ShowMessage(result.error.asUiText()))
                        }

                        is Result.Loading -> {}
                        is Result.Success -> {
                            _uiEvent.send(UiEvent.Success)
                        }
                    }
                }
        }
    }

    private fun onPermissionDialogDismiss() {
        _permissionQueue.removeFirst()
        _state.update {
            it.copy(
                permissionDialogQueue = _permissionQueue
            )
        }
    }

    private fun onPermissionResult(
        permission: String,
        isGranted: Boolean
    ) {
        if (!isGranted && !_permissionQueue.contains(permission)) {
            _permissionQueue.add(permission)
            _state.update {
                it.copy(
                    permissionDialogQueue = _permissionQueue
                )
            }
        }

    }

    private fun getCities() {
        viewModelScope.launch {
            getCitiesUseCase.invoke()
                .collect { citiesList ->
                    _state.update {
                        it.copy(cities = citiesList)
                    }
                }
        }
    }
}