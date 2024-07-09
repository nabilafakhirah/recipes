package com.example.recipes.ui.screens.recipelist.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.recipes.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBarView(
    onSearch: (String) -> Unit,
    onSortDesc: () -> Unit,
    onSortAsc: () -> Unit,
) {
    var name by remember { mutableStateOf("") }
    var showExpandedSearch by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Search") },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(0.9f),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    onSearch(name)
                    // Hide the keyboard after submitting the search
                    keyboardController?.hide()
                    //or hide keyboard
                    focusManager.clearFocus()
                })
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_sort),
                contentDescription = "Expand",
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        showExpandedSearch = !showExpandedSearch
                    }
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Spacer(modifier = Modifier.weight(1f))
            DropdownMenu(
                expanded = showExpandedSearch,
                onDismissRequest = { showExpandedSearch = false }
            ) {
                DropdownMenuItem(
                    onClick = {
                        onSortDesc()
                        showExpandedSearch = false
                    },
                ) {
                    Text(text = "Sort by Name A-Z")
                }
                DropdownMenuItem(
                    onClick = {
                        onSortAsc()
                        showExpandedSearch = false
                    },
                ) {
                    Text(text = "Sort by Name Z-A")
                }
            }
        }
    }
}