package com.breens.jetpackcomposeuiconcepts.taskmanager.data.userdata

import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    suspend fun insert(name: String, password: String, id: Long?)

    suspend fun delete(id: Long)

    fun getAll(): Flow<List<User>>

    suspend fun getBy(id: Long): User?

    fun getUserCount(): Flow<Int>

    fun getUserName(id: Long): Flow<String>

}