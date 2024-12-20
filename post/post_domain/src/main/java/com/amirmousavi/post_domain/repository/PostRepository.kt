package com.amirmousavi.post_domain.repository

import androidx.paging.PagingData
import com.amirmousavi.core.domain.model.PostEntity
import com.amirmousavi.post_domain.model.PostDetailEntity
import kotlinx.coroutines.flow.Flow

interface PostRepository {


    fun getPosts(): Flow<PagingData<PostEntity>>


    suspend fun syncPostDetail(token :String)

    fun getPostDetail(token :String): Flow<PostDetailEntity>

}