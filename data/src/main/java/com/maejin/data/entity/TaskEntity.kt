package com.maejin.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.maejin.todo.domain.model.Task

@Entity(tableName = "task")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val content: String,
    val isDone: Boolean = false
) {
    companion object {
        fun from(task: Task): TaskEntity {
            return TaskEntity(
                id = task.id,
                content = task.content,
                isDone = task.isDone
            )
        }
    }

    fun toDomainModel(): Task {
        return Task(
            id = id,
            content = content,
            isDone = isDone
        )
    }
}
