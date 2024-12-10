package com.amirmousavi.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amirmousavi.core.domain.model.CityEntity

@Database(entities = [CityEntity::class], version = 1)
abstract class DivarDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao
}