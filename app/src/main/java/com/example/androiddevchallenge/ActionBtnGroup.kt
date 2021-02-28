package com.example.androiddevchallenge

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.*

@Composable
fun ActionBtnGroup(
    onSort: (type: String) -> Unit,
    onFilter: (type: Int, keyword: Int) -> Unit,
) {
    val showFilterDialog = remember { mutableStateOf(false) }
    val showSortDialog = remember { mutableStateOf(false) }

    Row {
        IconButton(
            onClick = {
                showFilterDialog.value = true
            }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_filter_alt_white_24dp),
                contentDescription = "filter",
                tint = colors.surface
            )
        }
        IconButton(
            onClick = {
                showSortDialog.value = true
            }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_sort_white_24dp),
                contentDescription = "sort",
                tint = colors.surface
            )
        }
    }
    if (showFilterDialog.value) {
        ShowDialog(0, showFilterDialog, onSort, onFilter)
    }
    if (showSortDialog.value) {
        ShowDialog(1, showSortDialog, onSort, onFilter)
    }
}

@Composable
fun ShowDialog(
    type: Int, showFlag: MutableState<Boolean>,
    onSort: (type: String) -> Unit,
    onFilter: (type: Int, keyword: Int) -> Unit,
) {
    val sortOptions = listOf("Age", "Name")
    val (selectedSortOption, onSortOptionSelected) = remember { mutableStateOf(sortOptions[0]) }
    val (selectedSexOption, onSexOptionSelected) = remember { mutableStateOf(PetConstants.Sex.ALL) }
    val (selectedSpeciesOption, onSpeciesOptionSelected) = remember { mutableStateOf(PetConstants.Sex.ALL) }

    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
            showFlag.value = false
        },
        title = {
            Text(
                text = if (type == 0) {
                    "Filter"
                } else {
                    "Sort"
                },
                color = colors.onPrimary,
                style = typography.subtitle1.merge()
            )
        },
        text = {
            if (type == 0) {
                FilterCompose(
                    selectedSexOption, onSexOptionSelected,
                    selectedSpeciesOption, onSpeciesOptionSelected
                )
            } else {
                SortCompose(sortOptions, selectedSortOption, onSortOptionSelected)
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    showFlag.value = false
                    if (type == 0) {
                        onFilter(selectedSexOption, selectedSpeciesOption)
                    } else {
                        onSort(selectedSortOption)
                    }
                }
            ) {
                Text("Yes", color = Color.White)
            }
        }
    )
}

@Composable
fun FilterCompose(
    selectedSexType: Int,
    onSexTypeSelected: (Int) -> Unit,
    selectedSpeciesType: Int,
    onSpeciesTypeSelected: (Int) -> Unit,
) {
    val filterTypeOptionString = listOf("Sex", "Species")
    val sexOptionString = listOf("Female", "Male", "All")
    val speciesOptionString = listOf("Cat", "Dog", "All")
    Column {
        filterTypeOptionString.forEach { filterTypeString ->
            Column(
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text(
                    text = filterTypeString,
                    style = typography.body1.merge(),
                    color = colors.onPrimary,
                )
                Spacer(modifier = Modifier.padding(bottom = 4.dp))
                val index = filterTypeOptionString.indexOf(filterTypeString)
                val optionString = if (index == PetConstants.FilterType.SEX) {
                    sexOptionString
                } else {
                    speciesOptionString
                }
                Row {
                    optionString.forEach { filterKeywordString ->
                        val keyIndex = optionString.indexOf(filterKeywordString)
                        Row(
                            Modifier
                                .selectable(
                                    selected = (if (filterTypeString == "Sex") {
                                        selectedSexType == keyIndex
                                    } else {
                                        selectedSpeciesType == keyIndex
                                    }),
                                    onClick = {
                                        if (filterTypeString == "Sex") {
                                            onSexTypeSelected(keyIndex)
                                        } else {
                                            onSpeciesTypeSelected(keyIndex)
                                        }
                                    }
                                )
                                .padding(end = 16.dp)
                        ) {
                            RadioButton(
                                selected = (if (filterTypeString == "Sex") {
                                    selectedSexType == keyIndex
                                } else {
                                    selectedSpeciesType == keyIndex
                                }),
                                onClick = {
                                    if (filterTypeString == "Sex") {
                                        onSexTypeSelected(keyIndex)
                                    } else {
                                        onSpeciesTypeSelected(keyIndex)
                                    }
                                }
                            )
                            Text(
                                text = filterKeywordString,
                                color = colors.onSecondary,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SortCompose(
    sortOptions: List<String>, selectedSortOption: String,
    onSortOptionSelected: (String) -> Unit
) {
    Column {
        sortOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (text == selectedSortOption),
                        onClick = {
                            onSortOptionSelected(text)
                        }
                    )
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedSortOption),
                    onClick = { onSortOptionSelected(text) }
                )
                Text(
                    text = text,
                    modifier = Modifier.padding(start = 8.dp),
                    color = colors.onPrimary
                )
            }
        }
    }
}