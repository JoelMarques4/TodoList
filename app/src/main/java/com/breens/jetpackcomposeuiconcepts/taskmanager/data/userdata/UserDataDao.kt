package com.breens.jetpackcomposeuiconcepts.taskmanager.data.userdata

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: UserDataEntity)

    @Delete
    suspend fun delete(entity: UserDataEntity)

    @Query("SELECT * FROM user")
    fun getAll(): Flow<List<UserDataEntity>>

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getBy(id: Long): UserDataEntity?

    @Query("SELECT COUNT(*) FROM user")
    fun getUserCount(): Flow<Int>

    @Query("SELECT name FROM user WHERE id = :id")
    fun getUserName(id: Long): Flow<String>

}