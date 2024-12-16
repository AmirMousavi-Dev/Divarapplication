package com.amirmousavi.post_presentation.post_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.amirmousavi.core.domain.model.PostEntity
import com.amirmousavi.post_domain.usecase.GetPostListUseCase
import com.amirmousavi.post_presentation.util.PostWidgetFactory
import com.amirmousavi.post_presentation.util.Widget
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val getPostListUseCase: GetPostListUseCase,
) : ViewModel() {

    private val _postsList =
        MutableStateFlow<PagingData<Pair<PostEntity, Widget>>>(PagingData.empty())
    val postsList = _postsList.asStateFlow()


    fun getPosts() {
        viewModelScope.launch {
            getPostListUseCase()
                .collect { result ->
                    _postsList.update {
                        result.map { postEntity ->
                            Pair(
                                postEntity,
                                PostWidgetFactory.createWidget(postEntity.widgetType)
                            )
                        }
                    }
                }
        }
    }
}