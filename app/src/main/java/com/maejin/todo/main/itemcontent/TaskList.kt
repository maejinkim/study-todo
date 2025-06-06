package com.maejin.todo.main.itemcontent

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.maejin.todo.ui.theme.LightGray
import com.maejin.todo.ui.theme.RoseQuartz

@Composable
fun TaskList(
    modifier: Modifier = Modifier,
    onCheckedChange: (Task) -> Unit,
    onClickedDelete: (Task) -> Unit,
    tasks: List<Task>
) {
    LazyColumn(modifier = modifier.padding(20.dp)) {
        items(tasks.size) { index ->
            TaskItem(
                task = tasks[index],
                onCheckedChange = { isChecked ->
                    onCheckedChange(tasks[index].copy(isDone = isChecked))
                },
                onClickedDelete = { onClickedDelete(tasks[index]) },
                modifier = Modifier.fillMaxWidth()
            )

            if (index < tasks.size - 1) {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun TaskItem(
    task: Task,
    onCheckedChange: (Boolean) -> Unit,
    onClickedDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            modifier = Modifier
                .padding(end = 5.dp)
                .size(20.dp),
            checked = task.isDone,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = RoseQuartz,
                uncheckedColor = LightGray
            )
        )

        Text(
            text = task.content,
            modifier = Modifier.weight(1f),
            style = if (task.isDone) TextStyle(
                color = Color.Gray,
                textDecoration = TextDecoration.LineThrough
            ) else TextStyle.Default
        )

        IconButton(
            modifier = Modifier
                .padding(0.dp)
                .size(20.dp),
            onClick = onClickedDelete
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                tint = Color.DarkGray,
                contentDescription = "Delete Task"
            )
        }
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
        onClickedDelete = {}
    )
}