package com.amirmousavi.core.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amirmousavi.core.domain.model.CityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {

    @Query("""
        SELECT * 
        FROM tb_cities
        ORDER BY id ASC;
    """)
    fun getAllCities(): Flow<List<CityEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<CityEntity>)

    @Query("DELETE FROM tb_cities")
    suspend fun clearAll()

}