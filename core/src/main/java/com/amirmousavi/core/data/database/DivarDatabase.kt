package com.amirmousavi.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amirmousavi.core.domain.model.CityEntity
import com.amirmousavi.core.domain.model.PostEntity

@Database(entities = [CityEntity::class,PostEntity::class], version = 2, exportSchema = false)
abstract class DivarDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao

    abstract fun postDao(): PostDao
}