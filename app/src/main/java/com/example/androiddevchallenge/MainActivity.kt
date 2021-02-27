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
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.PetConstants.Sex.FEMALE
import com.example.androiddevchallenge.PetConstants.Sex.MALE
import com.example.androiddevchallenge.PetConstants.Species.CAT
import com.example.androiddevchallenge.PetConstants.Species.DOG
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.typography

class MainActivity : AppCompatActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun randomPet(): Pet {
    val name = PetConstants.names.random()
    val age = arrayOf(0, 1, 2, 3, 4, 5).random()
    val species = arrayOf(CAT, DOG).random()
    val sex = arrayOf(FEMALE, MALE).random()
    val headUrl = remember(name) {
        when (species) {
            CAT -> {
                PetConstants.catHeadImgs.random()
            }
            DOG -> {
                PetConstants.dogHeadImgs.random()
            }
            else -> {
                PetConstants.catHeadImgs.random()
            }
        }
    }

    return Pet(name, species, age, sex, headUrl)
}

@Composable
fun generatePetList(): List<Pet> {
    val listCount = arrayOf(10, 15, 20).random()
    val petList = emptyList<Pet>().toMutableList()
    for (i in 0..listCount) {
        petList.add(randomPet())
    }
    petList.distinctBy { it.name }
    return petList
}

// Start building your app here!
@ExperimentalAnimationApi
@Composable
fun MyApp() {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = colors.background,
        topBar = {
            TopAppBar(
                title = { Text("Pets") },
                backgroundColor = colors.primary,
                actions = {
                    ActionBtnGroup()
                }
            )
        },
    ) { PetList(pets = generatePetList()) }
}

@Composable
fun ActionBtnGroup() {
    val showFilterDialog = remember { mutableStateOf(false) }
    val showSortDialog = remember { mutableStateOf(false) }
    Row {
        IconButton(
            onClick = {
                showFilterDialog.value = true
            }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_filter_alt_white_24dp),
                contentDescription = "filter"
            )
        }
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
        showDialog(0, showFilterDialog)
    }
    if (showSortDialog.value) {
        showDialog(1, showSortDialog)
    }
}

@Composable
fun showDialog(type: Int, showFlag: MutableState<Boolean>) {
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
            if (type == 0) {
                FilterCompose()
            } else {
                SortCompose()
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    showFlag.value = false
                }
            ) {
                Text("Yes")
            }
        }
    )
}

@Composable
fun FilterCompose() {
    val radioOptions = listOf("Age", "Sex", "Species")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1]) }
    Column {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                        }
                    )
                    .padding(horizontal = 16.dp)
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = { onOptionSelected(text) }
                )
                Text(
                    text = text,
                    style = typography.body1.merge(),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Composable
fun SortCompose() {
    val radioOptions = listOf("Age", "Name")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
    Column {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                        }
                    )
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = { onOptionSelected(text) }
                )
                Text(
                    text = text,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@ExperimentalAnimationApi
@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
