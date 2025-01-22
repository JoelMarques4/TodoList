package com.breens.jetpackcomposeuiconcepts.taskmanager.data

import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun insert(title: String, body: String?, startTime: String, endTime: String)

    suspend fun updateCompleted(id: Long, isCompleted: Boolean)

    suspend fun delete(id: Long)

    fun getAll(): Flow<List<Task>>

    suspend fun getBy(id: Long): Task?

}