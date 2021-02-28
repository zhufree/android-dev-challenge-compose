/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.PetConstants.FilterType.SEX
import com.example.androiddevchallenge.PetConstants.FilterType.SPECIES
import com.example.androiddevchallenge.PetConstants.Sex.FEMALE
import com.example.androiddevchallenge.PetConstants.Sex.MALE
import com.example.androiddevchallenge.PetConstants.Species.CAT
import com.example.androiddevchallenge.PetConstants.Species.DOG
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.typography

class MainActivity : AppCompatActivity() {
    private val petViewModel by viewModels<PetViewModel>()

    @ExperimentalFoundationApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                petViewModel.createList()
                MyApp(petViewModel)
            }
        }
    }
}


// Start building your app here!
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun MyApp(petViewModel: PetViewModel) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = colors.background,
        topBar = {
            TopAppBar(
                title = { Text("Pets") },
                backgroundColor = colors.primary,
                actions = {
                    ActionBtnGroup(
                        onSort = petViewModel::sortList,
                        onFilter = petViewModel::filterList
                    )
                }
            )
        },
    ) {
        PetList(
            pets = petViewModel.petList,
            petGroup = petViewModel.petGroup
        )
    }
}

@Composable
fun ActionBtnGroup(
    onSort: (type: String) -> Unit,
    onFilter: (type: Int, keyword: Int) -> Unit,
) {
    val showFilterDialog = remember { mutableStateOf(false) }
    val showSortDialog = remember { mutableStateOf(false) }
    Row {
//        IconButton(
//            onClick = {
//                showFilterDialog.value = true
//            }) {
//            Icon(
//                painter = painterResource(id = R.drawable.baseline_filter_alt_white_24dp),
//                contentDescription = "filter"
//            )
//        }
        IconButton(
            onClick = {
                showSortDialog.value = true
            }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_sort_white_24dp),
                contentDescription = "filter"
            )
        }
    }
    if (showFilterDialog.value) {
        showDialog(0, showFilterDialog, onSort, onFilter)
    }
    if (showSortDialog.value) {
        showDialog(1, showSortDialog, onSort, onFilter)
    }
}

@Composable
fun showDialog(
    type: Int, showFlag: MutableState<Boolean>,
    onSort: (type: String) -> Unit,
    onFilter: (type: Int, keyword: Int) -> Unit,
) {
    val sortOptions = listOf("Age", "Name")
    val filterOptions = listOf(SEX, SPECIES)
    val (selectedSortOption, onSortOptionSelected) = remember { mutableStateOf(sortOptions[0]) }
    val (selectedFilterOption, onFilterOptionSelected) = remember { mutableStateOf(SEX) }
    val (filterKeywordOption, onFilterKeywordSelected) = remember {
        mutableStateOf(mapOf(0 to 0, 1 to 0).toMutableMap())
    }

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
                }
            )
        },
        text = {
//            if (type == 0) {
//                FilterCompose(
//                    filterOptions, selectedFilterOption, onFilterOptionSelected,
//                    filterKeywordOption, onFilterKeywordSelected
//                )
//            } else {
                SortCompose(sortOptions, selectedSortOption, onSortOptionSelected)
//            }
        },
        confirmButton = {
            Button(
                onClick = {
                    showFlag.value = false
                    if (type == 0) {
                        onFilter(selectedFilterOption, filterKeywordOption[selectedFilterOption]!!)
                    } else {
                        onSort(selectedSortOption)
                    }
                }
            ) {
                Text("Yes")
            }
        }
    )
}

@Composable
fun FilterCompose(
    filterTypeOptions: List<Int>,
    selectedFilterType: Int,
    onFilterTypeSelected: (Int) -> Unit,
    selectedFilterKeyword: MutableMap<Int, Int>,
    onFilterKeywordSelected: (MutableMap<Int, Int>) -> Unit,
) {
    val filterTypeOptionString = listOf("Sex", "Species")
    val sexOption = listOf(FEMALE, MALE)
    val sexOptionString = listOf("Female", "Male")
    val speciesOption = listOf(CAT, DOG)
    val speciesOptionString = listOf("Cat", "Dog")
    Column {
        filterTypeOptionString.forEach { filterTypeString ->
            Column(
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text(
                    text = filterTypeString,
                    style = typography.h6.merge(),
                )
                val index = filterTypeOptionString.indexOf(filterTypeString)
                val option = if (index == SEX) {
                    sexOption
                } else {
                    speciesOption
                }
                val optionString = if (index == SEX) {
                    sexOptionString
                } else {
                    speciesOptionString
                }
                Row {
                    optionString.forEach { filterKeywordString -> //0,1 / 0,1
                        val keyIndex = optionString.indexOf(filterKeywordString)
                        Row(Modifier
                            .selectable(
                                selected = (filterKeywordString == optionString[keyIndex]),
                                onClick = {
                                    onFilterTypeSelected(index)
                                    selectedFilterKeyword[index] = keyIndex
//                                    onFilterKeywordSelected(selectedFilterKeyword)
                                    println("select filter type: $selectedFilterType")
                                    println("select filter keyword: $selectedFilterKeyword")
                                    println("filter keyword: $filterKeywordString")
                                    println("light: ${filterKeywordString == optionString[keyIndex]}")
                                }
                            )
                            .padding(end = 16.dp)
                        ) {
                            Switch(
                                checked = selectedFilterKeyword[index] == keyIndex,
                                onCheckedChange = {
                                    onFilterTypeSelected(index)
                                    selectedFilterKeyword[index] = keyIndex
                                    onFilterKeywordSelected(selectedFilterKeyword)
                                }
                            )
                            Text(
                                text = filterKeywordString,
                                style = typography.body1.merge(),
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
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

//@ExperimentalAnimationApi
//@Preview("Light Theme", widthDp = 360, heightDp = 640)
//@Composable
//fun LightPreview() {
//    MyTheme {
//        MyApp(null)
//    }
//}

//@ExperimentalAnimationApi
//@Preview("Dark Theme", widthDp = 360, heightDp = 640)
//@Composable
//fun DarkPreview() {
//    MyTheme(darkTheme = true) {
//        MyApp()
//    }
//}
