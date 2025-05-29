package com.maejin.todo.main

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maejin.todo.domain.model.Task
import com.maejin.todo.main.itemcontent.InputBox
import com.maejin.todo.main.itemcontent.TaskList
import com.maejin.todo.ui.theme.TodoTheme


@Composable
fun MainScreen() {
    TodoTheme {
        val context: Context = LocalContext.current
        val viewModel : MainViewModel = hiltViewModel()

        val tasks: State<List<Task>> = viewModel.tasks.collectAsStateWithLifecycle()
        val (text, onTextChange) = remember { mutableStateOf("") }

        Column (
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            TaskList(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                tasks = tasks.value,
                onCheckedChange = { task ->
                    viewModel.updateTask(task)
                }
            )
            InputBox (
                text = text,
                onTextChange = onTextChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                onClick = {
                    viewModel.addTask(Task(content = text))
                    onTextChange("")
                    Toast.makeText(context, "Task added: $text", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}



