package com.amirmousavi.post_data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.amirmousavi.core.data.database.DivarDatabase
import com.amirmousavi.core.data.database.PostDao
import com.amirmousavi.core.data.database.PostViewDao
import com.amirmousavi.core.domain.model.PostEntity
import com.amirmousavi.core.util.BaseApiResponse
import com.amirmousavi.post_data.mapper.asPostDetailEntity
import com.amirmousavi.post_data.mapper.asPostViewDbEntity
import com.amirmousavi.post_data.remote.PostApiService
import com.amirmousavi.post_data.remote.PostMediator
import com.amirmousavi.post_domain.model.PostDetailEntity
import com.amirmousavi.post_domain.repository.PostRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PostRepositoryImpl(
    private val postDao: PostDao,
    private val postDetailDao: PostViewDao,
    private val apiService: PostApiService,
    private val database: DivarDatabase,
    private val gson: Gson,
) : PostRepository, BaseApiResponse() {


    @OptIn(ExperimentalPagingApi::class)
    override fun getPosts(): Flow<PagingData<PostEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            remoteMediator = PostMediator(apiService, database),
            pagingSourceFactory = {
                postDao.getAllPosts()
            }
        ).flow
    }

    override suspend fun syncPostDetail(token: String) {
        try {
            val response = apiService.getPostByToken(token)
            if (response.isSuccessful) {
                val body = response.body()
                body?.let { postViewDTO ->
                    postDetailDao.insertPostViewEntity(
                        postViewDTO.asPostViewDbEntity(token, gson)
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getPostDetail(token: String): Flow<PostDetailEntity> =
        postDetailDao.getPostView(token)
            .map {
                it.asPostDetailEntity(gson)
            }


}

