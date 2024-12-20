package com.amirmousavi.onboarding_presentation.explore_cities_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirmousavi.core.domain.datastore.DivarDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreCitiesViewModel @Inject constructor(
    private val dataStore: DivarDataStore
) : ViewModel() {


    fun shouldShowOnboarding(shouldShow: Boolean) {
        viewModelScope.launch {
            dataStore.saveShouldShowOnboarding(shouldShow)
        }
    }
}