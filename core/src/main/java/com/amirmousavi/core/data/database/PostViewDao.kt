package com.amirmousavi.core.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amirmousavi.core.data.model.PostViewDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PostViewDao {

    @Query("SELECT * FROM tb_post_view Where :token = token")
    fun getPostView(token :String): Flow<PostViewDbEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPostViewEntity(postViewDbEntity: PostViewDbEntity)




}