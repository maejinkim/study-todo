package com.maejin.todo.main.itemcontent

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maejin.todo.domain.model.Task

@Composable
fun TaskList(
    modifier: Modifier = Modifier,
    onCheckedChange: (Task) -> Unit,
    tasks: List<Task>
) {
    LazyColumn(modifier = modifier.padding(8.dp)) {
        items(tasks.size) { index ->
            TaskItem(
                task = tasks[index],
                onCheckedChange = { isChecked ->
                    onCheckedChange(tasks[index].copy(isDone = isChecked))
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun TaskItem(
    task: Task,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = task.isDone,
            onCheckedChange = onCheckedChange
        )
        Text(
            text = task.content,
            modifier = Modifier.weight(1f),
            style = if (task.isDone) TextStyle(
                color = Color.Gray,
                textDecoration = TextDecoration.LineThrough
            ) else TextStyle.Default
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskListPreview() {
    TaskList(
        tasks = listOf(
            Task(content = "Task 1"),
            Task(content = "Task 2", isDone = true),
            Task(content = "Task 3")
        ),
        onCheckedChange = {},
    )
}