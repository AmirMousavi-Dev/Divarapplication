package com.amirmousavi.post_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirmousavi.core.domain.model.CityEntity
import com.amirmousavi.post_domain.usecase.GetCitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectCityViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SelectCityContract.State())
    val state = _state.asStateFlow()


    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _cities = MutableStateFlow<List<CityEntity>>(emptyList())

    @OptIn(FlowPreview::class)
    val cities = searchQuery
        .debounce(300)
        .combine(_cities) {query ,cityList ->
            if (query.isBlank()){
                cityList
            } else {
                cityList.filter { it.name.contains(query) }
            }
        }.stateIn(viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _cities)


    fun onEvent(event: SelectCityContract.Event) {
        when (event) {
            is SelectCityContract.Event.GetCities -> getCities()

        }
    }

    @OptIn(FlowPreview::class)
    private fun getCities() {
        viewModelScope.launch {
            getCitiesUseCase.invoke()
                .debounce(300)
                .collect { citiesList ->
                    _state.update {
                        it.copy(cities = citiesList)
                    }
                }
        }
    }
}