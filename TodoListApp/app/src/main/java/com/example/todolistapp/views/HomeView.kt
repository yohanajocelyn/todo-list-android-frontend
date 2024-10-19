package com.example.todolistapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolistapp.R
import com.example.todolistapp.enums.PrioritiesEnum
import com.example.todolistapp.viewModels.HomeViewModel
import com.example.todolistapp.views.templates.TodoListCardTemplate

@Composable
fun HomeView(
    modifier: Modifier = Modifier,
    onAddButtonClicked: () -> Unit,
    homeViewModel: HomeViewModel = viewModel()
) {
    val todoList = homeViewModel.todoModel.collectAsState()

    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .padding(top = 20.dp)
        ) {
            Text(
                text = "Welcome",
                fontSize = 40.sp,
                fontWeight = FontWeight.Light,
                color = Color.Black
            )
            Text(
                text = "Asdf", // TODO: put username data here
                fontSize = 40.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
            colors = CardDefaults.cardColors(Color.Black)
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, top = 20.dp, end = 16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = 8.dp, bottom = 4.dp)
                        .fillMaxWidth(),

                    ) {
                    Text(
                        text = stringResource(R.string.today_tasks_text),
                        fontSize = 27.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                    )

                    Button(
                        onClick = onAddButtonClicked,
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(Color.White),
                        modifier = Modifier
                            .size(40.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_add),
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
                        )
                    }
                }

                LazyColumn(
                    flingBehavior = ScrollableDefaults.flingBehavior(),
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .clip(RoundedCornerShape(10.dp))
                ) {
                    items(todoList.value) { todo ->
                        TodoListCardTemplate(
                            title = todo.title,
                            priority = todo.priority,
                            dueDate = todo.dueDate,
                            status = todo.status,
                            priorityColor = homeViewModel.changePriorityTextBackgroundColor(todo.priority),
                            modifier = Modifier
                                .padding(bottom = 12.dp)
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
fun HomeViewPreview() {
    HomeView(
        onAddButtonClicked = {

        },
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
}