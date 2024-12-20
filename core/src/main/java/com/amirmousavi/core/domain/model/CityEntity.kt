package com.amirmousavi.core.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_cities")
data class CityEntity(
    @PrimaryKey
    val id: Int,
    val name: String
)