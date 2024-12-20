package com.amirmousavi.post_presentation.post_detail_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirmousavi.post_domain.usecase.GetPostDetailUseCase
import com.amirmousavi.post_presentation.model.ContactButtonUiModel
import com.amirmousavi.post_presentation.model.WidgetUiModel
import com.amirmousavi.post_presentation.util.PostWidgetFactory
import com.amirmousavi.post_presentation.util.Widget
import com.amirmousavi.post_presentation.util.asWidgetUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val getPostDetailUseCase: GetPostDetailUseCase
) : ViewModel() {

    private val _contactButtonState = MutableStateFlow<ContactButtonUiModel?>(null)
    val contactButtonState = _contactButtonState.asStateFlow()

    private val _widgetState = MutableStateFlow<List<Pair<WidgetUiModel, Widget>>>(emptyList())
    val widgetState = _widgetState.asStateFlow()


    fun getPostDetail(token: String) {
        viewModelScope.launch {
            getPostDetailUseCase(token)
                .collect { result ->
                    _widgetState.update {
                        result.widgets.map {
                            Pair(
                                it.widgetDataEntity.asWidgetUiModel(),
                                PostWidgetFactory.createWidget(it.widgetType)
                            )
                        }
                    }

                    _contactButtonState.update {
                        ContactButtonUiModel(
                            text = result.contactButtonText,
                            enable = result.enableContact
                        )
                    }


                }
        }
    }
}