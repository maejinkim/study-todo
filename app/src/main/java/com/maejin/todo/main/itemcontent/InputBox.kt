package com.maejin.todo.main.itemcontent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maejin.todo.ui.theme.Serenity

@Composable
fun InputBox(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    onClick: () -> Unit
) {
    Row(modifier = modifier) {

        OutlinedTextField(
            value = text,
            singleLine = true,
            onValueChange = onTextChange,
            modifier = Modifier
                .height(60.dp)
                .weight(1f, true)
                .background(Color.White),
            placeholder = { Text("Enter your task") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Serenity,
                unfocusedIndicatorColor = Serenity,
                cursorColor = Serenity
            ),
            keyboardActions = KeyboardActions(
                onDone = { onClick() }
            )
        )

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            modifier = Modifier
                .width(80.dp)
                .height(60.dp),
            shape = RoundedCornerShape(5.dp),
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Serenity,
                contentColor = Color.White
            )
        ) {
            Text("ADD")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun InputBoxPreview() {
    InputBox(Modifier, "", {}, {})
}