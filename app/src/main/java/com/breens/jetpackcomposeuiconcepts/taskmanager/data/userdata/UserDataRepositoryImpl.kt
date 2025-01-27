package com.breens.jetpackcomposeuiconcepts.taskmanager.data.userdata

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserDataRepositoryImpl(
    private val dao: UserDataDao
) : UserDataRepository {

    override suspend fun insert(name: String, password: String, id: Long?) {
        val entity = id?.let{
            dao.getBy(it)?.copy(
                name = name,
                password = password
            )
        } ?: UserDataEntity(
            name = name,
            password = password,
        )

        dao.insert(entity)
    }

    override suspend fun delete(id: Long) {
        val existentEntity = dao.getBy(id) ?: return
        dao.delete(existentEntity)
    }

    override fun getAll(): Flow<List<User>> {
        return dao.getAll().map { entities ->
            entities.map{ entity ->
                User(
                    id = entity.id,
                    name = entity.name,
                    password = entity.password,
                )
            }

        }
    }

    override suspend fun getBy(id: Long): User? {
        return dao.getBy(id)?.let { entity ->
            User(
                id = entity.id,
                name = entity.name,
                password = entity.password
            )
        }
    }

    override fun getUserCount(): Flow<Int> {
        return dao.getUserCount()
    }

    override fun getUserName(id: Long): Flow<String> {
       return dao.getUserName(id)
    }


}