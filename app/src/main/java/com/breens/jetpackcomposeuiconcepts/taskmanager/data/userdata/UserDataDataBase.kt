package com.breens.jetpackcomposeuiconcepts.taskmanager.data.userdata

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [UserDataEntity::class],
    version = 1,
)
abstract class UserDataDataBase: RoomDatabase() {

    abstract val userDao: UserDataDao
}

object UserDataDatabaseProvider {

    @Volatile
    private var INSTANCE: UserDataDataBase? = null

    fun provide(context: Context): UserDataDataBase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                UserDataDataBase::class.java,
                "user-data"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}