package com.example.todolistapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolistapp.R

@Composable
fun TodoListDetailView (
    modifier: Modifier = Modifier,
    onBackButtonClick: () -> Unit,
    onEditButtonClick: () -> Unit,
    onDeleteButtonClick: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = onBackButtonClick,
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .size(40.dp),
                colors = ButtonDefaults.buttonColors(Color.Black)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .padding(10.dp)
                )
            }

            Row {
                Button(
                    onClick = onEditButtonClick,
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .size(40.dp),
                    colors = ButtonDefaults.buttonColors(Color.Blue)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_update),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.White),
                        modifier = Modifier
                            .padding(10.dp)
                    )
                }

                Spacer(modifier = Modifier.padding(horizontal = 4.dp))

                Button(
                    onClick =   onDeleteButtonClick,
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .size(40.dp),
                    colors = ButtonDefaults.buttonColors(Color.Red)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_delete),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.White),
                        modifier = Modifier
                            .padding(10.dp)
                    )
                }
            }
        }

        // add image here if you need

        Text(
            text = "Task Title",
            fontSize = 35.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(top = 16.dp)
        )

        Row(
            modifier = Modifier
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.status_detail_text),
                fontSize = 17.sp
            )
            Text(
                text = "Finished",
                fontSize = 17.sp,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Black)
                    .padding(4.dp),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            modifier = Modifier
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.priority_detail_text),
                fontSize = 17.sp
            )
            Text(
                text = "High",
                fontSize = 17.sp,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Black)
                    .padding(4.dp),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            modifier = Modifier
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.due_date_detail_text),
                fontSize = 17.sp
            )
            Text(
                text = "14 October 2026",
                fontSize = 17.sp,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Black)
                    .padding(4.dp),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier
                .padding(top = 16.dp),
        ) {
            Text(
                text = stringResource(R.string.description_detail_text),
                fontSize = 17.sp,
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )

            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                fontSize = 17.sp,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Black)
                    .padding(8.dp),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun TodoListDetailViewPreview() {
    TodoListDetailView(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        onDeleteButtonClick = {},
        onBackButtonClick = {},
        onEditButtonClick = {}
    )
}