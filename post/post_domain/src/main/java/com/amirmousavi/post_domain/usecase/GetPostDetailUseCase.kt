package com.amirmousavi.post_domain.usecase

import com.amirmousavi.post_domain.model.PostDetailEntity
import com.amirmousavi.post_domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostDetailUseCase @Inject constructor(
    private val postRepository: PostRepository
) {

    suspend operator fun invoke(token: String): Flow<PostDetailEntity> {
        postRepository.syncPostDetail(token)
        return postRepository.getPostDetail(token)
    }
}
