package com.maejin.todo.domain

data class Task (
    val content: String,
    val isChecked : Boolean = false
)