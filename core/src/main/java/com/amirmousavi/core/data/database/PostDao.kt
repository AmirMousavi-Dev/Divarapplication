package com.amirmousavi.core.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.amirmousavi.core.domain.model.PostEntity

@Dao
interface PostDao {

    @Query(
        """
        SELECT * 
        FROM tb_post
        ORDER BY id ASC
        """
    )
    fun getAllPosts(): PagingSource<Int, PostEntity>


    @Upsert
    suspend fun insertAll(posts: List<PostEntity>)

    @Query("DELETE FROM tb_post")
    suspend fun clearAll()

}