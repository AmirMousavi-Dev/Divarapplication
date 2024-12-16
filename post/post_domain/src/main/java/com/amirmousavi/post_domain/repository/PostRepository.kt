package com.amirmousavi.post_domain.repository

import androidx.paging.PagingData
import com.amirmousavi.core.domain.model.PostEntity
import kotlinx.coroutines.flow.Flow

interface PostRepository {


    fun getPosts(): Flow<PagingData<PostEntity>>

}