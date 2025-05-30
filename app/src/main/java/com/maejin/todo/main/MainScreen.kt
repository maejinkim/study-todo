package com.maejin.todo.main

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maejin.todo.domain.model.Task
import com.maejin.todo.main.itemcontent.InputBox
import com.maejin.todo.main.itemcontent.TaskList
import com.maejin.todo.ui.theme.RoseQuartz
import com.maejin.todo.ui.theme.TodoTheme


@Composable
fun MainScreen() {
    TodoTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TodoTopBar() },
            content = { paddingValues ->
                MainContent(modifier = Modifier.padding(paddingValues))
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "To-Do List",
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )
            )
        },
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = RoseQuartz
        )
    )
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current
) {
    val viewModel: MainViewModel = hiltViewModel()

    val tasks: State<List<Task>> = viewModel.tasks.collectAsStateWithLifecycle()
    val (text, onTextChange) = remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current

    Column (
        modifier = modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        TaskList(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            tasks = tasks.value,
            onCheckedChange = { task ->
                viewModel.updateTask(task)
            },
            onClickedDelete = { task ->
                viewModel.deleteTask(task)
                Toast.makeText(context, "Task deleted: ${task.content}", Toast.LENGTH_SHORT).show()
            }
        )
        InputBox (
            text = text,
            onTextChange = onTextChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            onClick = {
                viewModel.addTask(Task(content = text))
                onTextChange("")
                keyboardController?.hide()
                Toast.makeText(context, "Task added: $text", Toast.LENGTH_SHORT).show()
            }
        )
    }
}



