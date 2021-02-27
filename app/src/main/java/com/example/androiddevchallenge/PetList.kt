package com.example.androiddevchallenge

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@Preview("Pet List", widthDp = 360, heightDp = 640)
@Composable
fun PetListPreview() {
    MyTheme {
        PetList(generatePetList())
    }
}

@ExperimentalAnimationApi
@Composable
fun PetList(
    pets: List<Pet>,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        val listState = rememberLazyListState()
        val scope = rememberCoroutineScope()
        val showPetDialog = remember { mutableStateOf(false) }
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(bottom = 80.dp, start = 8.dp, end = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
        ) {
            val nameGrouped = pets.groupBy { it.name }
            val ageGrouped = pets.groupBy { it.age }
            val sexGrouped = pets.groupBy { it.sex }
            val speciesGrouped = pets.groupBy { it.species }
            nameGrouped.forEach { initial, pets ->
                item {
                }
//                stickyHeader {  }
                items(pets) { pet ->
                    PetListItem(pet, modifier = Modifier.clickable {
                        showPetDialog.value = true
                    })
                }
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
        // Show the button if the first visible item is past
        // the first item. We use a remembered derived state to
        // minimize unnecessary compositions
        val showButton = listState.firstVisibleItemIndex > 0

        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            ScrollToTopButton(
                onClick = {
                    scope.launch {
                        // Animate scroll to the first item
                        listState.animateScrollToItem(index = 0)
                    }
                },
            )
        }
    }

}

@Composable
fun PetListItem(pet: Pet, modifier: Modifier = Modifier) {
    Card(
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(6.dp),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth()
        ) {
            CoilImage(
                data = pet.headUrl,
                contentDescription = "picture of the pet",
                modifier = Modifier
                    .size(70.dp, 70.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(shape = RoundedCornerShape(35.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    pet.showSpecies(),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    pet.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(
                    pet.showAge(),
                    modifier = Modifier.padding(end = 8.dp),
                    fontSize = 14.sp
                )
                Text(pet.showSex(), fontSize = 14.sp)
            }
        }
    }
}


@Preview("btn_", widthDp = 50, heightDp = 50)
@Composable
fun ScrollToTopButton(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    FloatingActionButton(
        onClick = onClick,
        backgroundColor = Color.White
    ) {
        Box{
            Image(
                painter = painterResource(R.drawable.baseline_vertical_align_top_white_24dp),
                contentDescription = "top btn",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

