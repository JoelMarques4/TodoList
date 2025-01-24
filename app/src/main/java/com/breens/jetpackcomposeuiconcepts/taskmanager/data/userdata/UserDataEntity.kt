package com.breens.jetpackcomposeuiconcepts.taskmanager.data.userdata

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserDataEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val password: String,
)