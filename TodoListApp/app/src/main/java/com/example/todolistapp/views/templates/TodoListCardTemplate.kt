package com.example.todolistapp.views.templates

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolistapp.R
import com.example.todolistapp.enums.PrioritiesEnum
import com.example.todolistapp.viewModels.HomeViewModel

@Composable
fun TodoListCardTemplate(
    title: String,
    priority: PrioritiesEnum,
    dueDate: String,
    status: String,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = viewModel()
) {
    Card(
        onClick = { /*TODO*/ },
        modifier = modifier,
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(15.dp)
        ) {
            Column() {
                Text(
                    text = title,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .clip(RoundedCornerShape(100.dp))
                                .background(homeViewModel.changePriorityTextBackgroundColor(priority))
                        )

                        Text(
                            text = priority.name,
                            fontSize = 13.sp,
                            modifier = Modifier
                                .padding(start = 4.dp),
                            fontWeight = FontWeight.Medium
                        )
                    }

                    // kasi icon jam
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_time),
                            contentDescription = null,
                            modifier = Modifier
                                .size(16.dp),
                        )

                        Text(
                            text = dueDate,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .padding(start = 4.dp)
                        )
                    }

                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_task),
                            contentDescription = null,
                            modifier = Modifier
                                .size(16.dp),
                        )

                        Text(
                            text = status,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .padding(start = 4.dp)
                        )
                    }
                }
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
        title = "Print Hello World",
        status = "On Going",
        priority = PrioritiesEnum.High,
        dueDate = "20 September 2022",
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}