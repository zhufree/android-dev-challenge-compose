package com.example.androiddevchallenge

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun PetDetailScreen(petViewModel: PetViewModel, petId: Int,navigateBack: () -> Unit) {
    val pet = petViewModel.getPet(petId)
    val cs150 = CornerSize(100.dp)
    val showPetDialog = remember { mutableStateOf(false) }
    val gotPet = remember { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = colors.background,
        topBar = {
            TopAppBar(
                title = { Text("Pets") },
                backgroundColor = colors.primary,
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navigateBack()
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_white_24dp),
                            contentDescription = "filter"
                        )
                    }
                }
            )
        },
    ) {
        Column {
            Spacer(modifier = Modifier.height(80.dp))
            CoilImage(
                data = pet.headUrl,
                contentDescription = "picture of the pet",
                modifier = Modifier
                    .size(200.dp, 200.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(shape = RoundedCornerShape(cs150, cs150, cs150, cs150))
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = pet.showDetail(),
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.padding(16.dp)
            )
            if (gotPet.value) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_done_green_a200_48dp),
                    contentDescription = "got pet",
                    modifier = Modifier
                        .padding(40.dp)
                        .align(Alignment.CenterHorizontally),
                    tint = Color.Green
                )
                Text(
                    text = "Congratulation!",
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally),
                )
            } else {
                Button(
                    onClick = {
                        showPetDialog.value = true
                    },
                    modifier = Modifier
                        .padding(40.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Yes I want it!")
                }
            }
            if (showPetDialog.value) {
                AlertDialog(
                    onDismissRequest = {
                        // Dismiss the dialog when the user clicks outside the dialog or on the back
                        // button. If you want to disable that functionality, simply use an empty
                        // onCloseRequest.
                        showPetDialog.value = false
                    },
                    title = {
                        Text(text = "Do you want this pet?")
                    },
                    text = {
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                showPetDialog.value = false
                                gotPet.value = true
                            }) {
                            Text("Yes")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = {
                                showPetDialog.value = false
                            }) {
                            Text("Not sure now")
                        }
                    }
                )
            }
        }
    }
}