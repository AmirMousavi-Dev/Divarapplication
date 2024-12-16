package com.amirmousavi.post_data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.amirmousavi.core.data.database.DivarDatabase
import com.amirmousavi.core.domain.model.PostEntity
import com.amirmousavi.post_data.mapper.asListOfPostEntity
import com.amirmousavi.post_data.model.GetCityByIdRequest
import javax.inject.Inject

@ExperimentalPagingApi
class PostMediator @Inject constructor(
    private val apiService: PostApiService,
    private val database: DivarDatabase
) : RemoteMediator<Int, PostEntity>() {

    private var lastPostDate: Long = 0
    private var currentPage: Int = 0

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PostEntity>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    lastItem?.id ?: return MediatorResult.Success(endOfPaginationReached = true)
                    currentPage += 1
                    currentPage


                }

                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            }

            val response =
                apiService.getPostByCityId(1, GetCityByIdRequest(page, lastPostDate))
            lastPostDate = response.lastPostDate

            database.withTransaction {

                // درخواست داده‌ها از API


                if (loadType == LoadType.REFRESH) {
                    database.postDao().clearAll()
                }
                database.postDao().insertAll(response.asListOfPostEntity().take(10))
            }

            MediatorResult.Success(endOfPaginationReached = response.postWidgetDTOList.isEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}