package com.example.todolistapp.views.templates

import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Popup
import com.example.todolistapp.R

@Composable
fun TodoListOutlinedTextField(
    inputValue: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    labelText: String,
    placeholderText: String,
    minLine: Int,
    maxLine: Int
) {
    OutlinedTextField(
        value = inputValue,
        onValueChange = {
            onValueChange(it)
        },
        label = {
            Text(text = labelText )
        },
        placeholder = {
            Text(text = placeholderText)
        },
        modifier = modifier,
        minLines = minLine,
        maxLines = maxLine
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListDropdown(
    dropdownMenuExpanded: Boolean,
    onDropdownMenuBoxExpandedChange: (Boolean) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    dropdownItems: List<String>,
    onDropdownItemClick: (String) -> Unit,
    selectedItem: String,
    labelText: String
) {
    ExposedDropdownMenuBox(
        expanded = dropdownMenuExpanded,
        onExpandedChange = onDropdownMenuBoxExpandedChange,
        modifier = modifier
    ) {
        OutlinedTextField(
            modifier = Modifier
                .menuAnchor(),
            value = selectedItem,
            label = {
                Text(text = labelText)
            },
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropdownMenuExpanded)
            },
        )
        
        ExposedDropdownMenu(
            expanded = dropdownMenuExpanded,
            onDismissRequest = onDismissRequest
        ) {
            dropdownItems.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(text = item)
                    },
                    onClick = {
                        onDropdownItemClick(item)
                        onDismissRequest()
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListDatePicker(
    datePickerValue: String,
) {
    // TODO: Make the date picker here
}