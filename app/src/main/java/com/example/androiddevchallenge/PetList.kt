package com.example.androiddevchallenge

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
//        PetList(generatePetList(), {}, {_,_->})
    }
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun PetList(
    navigateToPetDetail: (petId: Int) -> Unit,
    pets: List<Pet>,
    petGroup: Map<String, List<Pet>>,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        val listState = rememberLazyListState()
        val scope = rememberCoroutineScope()
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(top = 8.dp, bottom = 80.dp, start = 8.dp, end = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if (petGroup.isNotEmpty()) {
                petGroup.keys.forEach { key ->
                    stickyHeader {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(colors.background)
                        ) {
                            Text(
                                key,
                                color = colors.onSecondary,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                        }
                    }
                    items(petGroup[key] as List<Pet>) { pet ->
                        PetListItem(pet, modifier = Modifier.clickable {
                            navigateToPetDetail(pets.indexOf(pet))
                        })
                    }
                }
            } else {
                items(pets) { pet ->
                    PetListItem(pet, modifier = Modifier.clickable {
                        navigateToPetDetail(pets.indexOf(pet))
                    })
                }
            }
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
    val cd6 = CornerSize(6.dp)
    val cd35 = CornerSize(35.dp)
    Card(
        elevation = 2.dp,
        backgroundColor = colors.onBackground,
        shape = RoundedCornerShape(cd6, cd6, cd6, cd6),
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
                    .clip(shape = RoundedCornerShape(cd35, cd35, cd35, cd35))
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
                    color = colors.onPrimary,
                    fontFamily = FontFamily.Monospace
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(
                    pet.showAge(),
                    modifier = Modifier.padding(end = 8.dp),
                    fontSize = 14.sp,
                    color = colors.onSecondary,
                )
                Text(pet.showSex(), fontSize = 14.sp, color = colors.onSecondary)
            }
        }
    }
}


@Preview("btn_", widthDp = 50, heightDp = 50)
@Composable
fun ScrollToTopButton(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    FloatingActionButton(
        onClick = onClick,
        backgroundColor = colors.onBackground
    ) {
        Box {
            Image(
                painter = painterResource(R.drawable.baseline_vertical_align_top_white_24dp),
                contentDescription = "top btn",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

