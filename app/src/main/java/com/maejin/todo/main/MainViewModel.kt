package com.maejin.todo.main

import androidx.lifecycle.ViewModel
import com.maejin.todo.domain.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _tasks : MutableStateFlow<List<Task>> = MutableStateFlow(emptyList())
    val tasks : StateFlow<List<Task>> = _tasks

    fun addTask(task: Task) {
        _tasks.value += task
    }

    fun updateTask(index: Int, task: Task) {
        if (index in _tasks.value.indices) {
            _tasks.value = _tasks.value.toMutableList().apply {
                this[index] = task
            }
        }
    }
}
