package com.example.todolistapp.views

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolistapp.R
import com.example.todolistapp.viewModels.TodoListFormViewModel
import com.example.todolistapp.views.templates.TodoListDatePicker
import com.example.todolistapp.views.templates.TodoListDropdown
import com.example.todolistapp.views.templates.TodoListOutlinedTextField

@Composable
fun TodoListFormView(
    todoListFormViewModel: TodoListFormViewModel = viewModel(),
    modifier: Modifier = Modifier,
    context: Context,
    onCancelButtonClick: () -> Unit,
    onSaveButtonClick: () -> Unit
) {
    val todoListFormUIState = todoListFormViewModel.todoListFormUIState.collectAsState()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column {
            Text(
                text = "Todo List Form",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 15.dp)
            )

            TodoListOutlinedTextField(
                inputValue = todoListFormViewModel.titleInput,
                onValueChange = {
                    todoListFormViewModel.changeTitleInput(it)
                    todoListFormViewModel.checkNullFormValues()
                },
                modifier = Modifier
                    .fillMaxWidth(),
                labelText = stringResource(R.string.title_text),
                placeholderText = stringResource(R.string.title_text),
                minLine = 1,
                maxLine = 1
            )

            TodoListOutlinedTextField(
                inputValue = todoListFormViewModel.descriptionInput,
                onValueChange = {
                    todoListFormViewModel.changeDescriptionInput(it)
                    todoListFormViewModel.checkNullFormValues()
                },
                labelText = stringResource(R.string.description_text),
                placeholderText = stringResource(R.string.description_text),
                minLine = 10,
                maxLine = 10,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            )

            Row(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth()
            ) {
                TodoListDropdown(
                    dropdownMenuExpanded = todoListFormUIState.value.statusDropdownExpandedValue,
                    onDismissRequest = {
                        todoListFormViewModel.dismissStatusDropdownMenu()
                    },
                    dropdownItems = todoListFormUIState.value.statusDropdownItems,
                    onDropdownItemClick = {
                        todoListFormViewModel.changeStatusInput(it)
                        todoListFormViewModel.checkNullFormValues()
                    },
                    onDropdownMenuBoxExpandedChange = {
                        todoListFormViewModel.changeStatusExpandedValue(it)
                    },
                    selectedItem = todoListFormViewModel.statusInput,
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .weight(1f),
                    labelText = "Status"
                )

                TodoListDropdown(
                    dropdownMenuExpanded = todoListFormUIState.value.priorityDropdownExpandedValue,
                    onDismissRequest = {
                        todoListFormViewModel.dismissPriorityDropdownMenu()
                    },
                    dropdownItems = todoListFormUIState.value.priorityDropdownItems,
                    onDropdownItemClick = {
                        todoListFormViewModel.changePriorityInput(it)
                        todoListFormViewModel.checkNullFormValues()
                    },
                    onDropdownMenuBoxExpandedChange = {
                        todoListFormViewModel.changePriorityExpandedValue(it)
                    },
                    selectedItem = todoListFormViewModel.priorityInput,
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .weight(1f),
                    labelText = "Priority"
                )
            }

            // TODO: Date Picker
            TodoListDatePicker(
                datePickerValue = todoListFormViewModel.dueDateInput,
                showCalendarDialog = {
                    todoListFormViewModel.showDatePickerDialog(todoListFormViewModel.initDatePickerDialog(context))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )
        }

        Column {
            Button(
                onClick = onCancelButtonClick,
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color.Gray)
            ) {
                Text(text = stringResource(R.string.cancel_text))
            }

            Button(
                onClick = onSaveButtonClick,
                modifier = Modifier
                    .fillMaxWidth(),
                enabled = todoListFormUIState.value.saveButtonEnabled,
                colors = ButtonDefaults.buttonColors(todoListFormViewModel.changeSaveButtonColor())
            ) {
                Text(text = stringResource(R.string.save_text))
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun CreateTodoListViewPreview() {
    TodoListFormView(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .padding(top = 8.dp),
        context = LocalContext.current,
        onCancelButtonClick = {},
        onSaveButtonClick = {}
    )
}