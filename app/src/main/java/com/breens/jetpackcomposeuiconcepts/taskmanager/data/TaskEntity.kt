package com.breens.jetpackcomposeuiconcepts.taskmanager.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val body: String? = null,
    val startTime: String,
    val endTime: String,
    val isCompleted: Boolean

)