package com.maejin.data.repository

import com.maejin.data.TaskDao
import com.maejin.data.entity.TaskEntity
import com.maejin.todo.domain.model.Task
import com.maejin.todo.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class TaskRepositoryImpl @Inject constructor(
    private val dao: TaskDao
) : TaskRepository {

    override fun getAllTasks(): Flow<List<Task>> =
        dao.getAllTasks().map { taskEntities -> taskEntities.map { it.toDomainModel() } }

    override suspend fun insertTask(task: Task) {
        dao.insertTask(TaskEntity.from(task))
    }

    override suspend fun updateTask(task: Task) {
        dao.updateTask(TaskEntity.from(task))
    }

    override suspend fun deleteTask(task: Task) {
        dao.deleteTask(TaskEntity.from(task))
    }
}