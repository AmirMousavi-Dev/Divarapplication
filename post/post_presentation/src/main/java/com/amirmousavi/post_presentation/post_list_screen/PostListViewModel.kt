package com.amirmousavi.post_presentation.post_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.amirmousavi.post_domain.usecase.GetPostListUseCase
import com.amirmousavi.post_presentation.util.PostWidgetFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
      getPostListUseCase: GetPostListUseCase,
) : ViewModel() {



    val postsList = getPostListUseCase()
        .map { pagingData ->
            pagingData.map { postEntity ->
                Pair(
                    postEntity,
                    PostWidgetFactory.createWidget(postEntity.widgetType)
                )
            }
        }.cachedIn(viewModelScope)


}