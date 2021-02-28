package com.example.androiddevchallenge

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun PetListScreen(
    petViewModel: PetViewModel,
    navigateToPetDetail: (petId: Int) -> Unit
) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = colors.background,
        topBar = {
            TopAppBar(
                title = { Text("Pets", color = colors.surface) },
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
            navigateToPetDetail,
            pets = petViewModel.petList,
            petGroup = petViewModel.petGroup
        )
    }
}