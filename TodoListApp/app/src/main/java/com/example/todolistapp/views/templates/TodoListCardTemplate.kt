package com.example.todolistapp.views.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TodoListCardTemplate(
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { /*TODO*/ },
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .padding(15.dp)
        ) {
            Column() {
                Text(
                    text = "Title",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = 16.dp)
                ) {
                    Text(
                        text = "Priority",
                        fontSize = 15.sp,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.Red)
                            .padding(vertical = 6.dp, horizontal = 10.dp),
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )

                    // kasi icon jam

                    Text(
                        text = "Due Date",
                        modifier = Modifier
                            .padding(start = 16.dp),
                        fontSize = 15.sp
                    )

                    // kasi icon task

                    // kasi text to do, on going, atau finished
                }
            }

            Column {

            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun TodoListCardTemplatePreview() {
    TodoListCardTemplate(
        modifier = Modifier
            .fillMaxWidth()
            .padding(7.dp)
    )
}