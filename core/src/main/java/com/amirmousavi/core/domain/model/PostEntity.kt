package com.amirmousavi.core.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_post")
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id :Int? = null,
    val widgetType:String,
    val text :String? = null,
    val title :String? = null,
    val token :String? = null,
    val price :String? = null,
    val thumbnail :String? = null,
    val district :String? = null,
)