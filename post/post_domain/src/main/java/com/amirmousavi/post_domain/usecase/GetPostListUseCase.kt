package com.amirmousavi.post_domain.usecase

import androidx.paging.PagingData
import com.amirmousavi.core.domain.model.PostEntity
import com.amirmousavi.post_domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostListUseCase @Inject constructor(
    private val postRepository: PostRepository
) {

    operator fun invoke(): Flow<PagingData<PostEntity>> {
        return postRepository.getPosts()
    }
}
