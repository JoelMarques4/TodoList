package com.breens.jetpackcomposeuiconcepts.taskmanager.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(
    private val dao: TaskDao
) : TaskRepository {

    override suspend fun insert(title: String, body: String?, startTime: String, endTime: String) {
        val entity = TaskEntity(
            title = title,
            body = body,
            startTime = "",
            endTime = "",
            isCompleted = false
        )

        dao.insert(entity)
    }

    override suspend fun updateCompleted(id: Long, isCompleted: Boolean) {
        val existentEntity = dao.getBy(id) ?: return
        val updatedEntity = existentEntity.copy(isCompleted = isCompleted)
        dao.insert(updatedEntity)
    }

    override suspend fun delete(id: Long) {
        val existentEntity = dao.getBy(id) ?: return
        dao.delete(existentEntity)
    }

    override fun getAll(): Flow<List<Task>> {
        return dao.getAll().map { entities ->
            entities.map{ entity ->
                Task(
                    id = entity.id,
                    title = entity.title,
                    body = entity.body,
                    startTime = entity.startTime,
                    endTime = entity.endTime,
                    isCompleted = entity.isCompleted
                )
            }

        }
    }

    override suspend fun getBy(id: Long): Task? {
        return dao.getBy(id)?.let { entity ->
            Task(
                id = entity.id,
                title = entity.title,
                body = entity.body,
                startTime = entity.startTime,
                endTime = entity.endTime,
                isCompleted = entity.isCompleted
            )
        }
    }


}