package com.amirmousavi.post_presentation.select_city_screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirmousavi.post_domain.usecase.GetCitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectCityViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SelectCityContract.State())
    val state = _state.asStateFlow()


    private val _permissionQueue = mutableStateListOf<String>()



    fun onEvent(event: SelectCityContract.Event) {
        when (event) {
            is SelectCityContract.Event.GetCities -> getCities()
            is SelectCityContract.Event.OnPermissionResult -> onPermissionResult(permission = event.permission , isGranted = event.isGranted)
            SelectCityContract.Event.OnPermissionDialogDismiss -> onPermissionDialogDismiss()
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
        permission :String,
        isGranted:Boolean
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