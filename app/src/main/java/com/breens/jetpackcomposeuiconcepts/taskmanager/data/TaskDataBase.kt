package com.breens.jetpackcomposeuiconcepts.taskmanager.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [TaskEntity::class],
    version = 1,
)
abstract class TaskDataBase: RoomDatabase() {

    abstract val taskDao: TaskDao
}

object TodoDatabaseProvider {

    @Volatile
    private var INSTANCE: TaskDataBase? = null

    fun provide(context: Context): TaskDataBase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                TaskDataBase::class.java,
                "task-app"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}