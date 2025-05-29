package com.maejin.todo.domain.model

data class Task (
    val id: Long = 0L,
    val content: String,
    val isDone : Boolean = false
)