package com.amirmousavi.post_data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.amirmousavi.core.data.database.DivarDatabase
import com.amirmousavi.core.data.database.PostDao
import com.amirmousavi.core.domain.model.PostEntity
import com.amirmousavi.core.util.BaseApiResponse
import com.amirmousavi.post_data.remote.PostApiService
import com.amirmousavi.post_data.remote.PostMediator
import com.amirmousavi.post_domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow

class PostRepositoryImpl(
    private val dao: PostDao,
    private val apiService: PostApiService,
    private val database: DivarDatabase,
) : PostRepository, BaseApiResponse() {

//
//    override suspend fun syncPosts(cityId :Int) {
//        try {
//            val response = apiService.getPostByCityId(cityId , GetCityByIdRequest(0,0))
//            if (response.isSuccessful) {
//                val body = response.body()
//                body?.let { postListDTO ->
//                    dao.insertAll(postListDTO.asListOfPostEntity())
//                }
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    override fun getPosts(): Flow<List<PostEntity>> = dao.getAllPosts()


    @OptIn(ExperimentalPagingApi::class)
    override fun getPosts(): Flow<PagingData<PostEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            remoteMediator = PostMediator(apiService, database),
            pagingSourceFactory = {
                dao.getAllPosts()
            }
        ).flow
    }


}

