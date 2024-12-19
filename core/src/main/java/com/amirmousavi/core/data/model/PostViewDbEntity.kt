package com.amirmousavi.core.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_post_view")
data class PostViewDbEntity(
    @PrimaryKey
    val token: String,
    val contactButtonText: String,
    val enableContact: Boolean,
    val widgets: String
)