package com.example.androiddevchallenge

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.PetConstants.Sex.FEMALE
import com.example.androiddevchallenge.PetConstants.Sex.MALE
import com.example.androiddevchallenge.PetConstants.SortType.AGE
import com.example.androiddevchallenge.PetConstants.SortType.NAME
import com.example.androiddevchallenge.PetConstants.Species.CAT
import com.example.androiddevchallenge.PetConstants.Species.DOG

class PetViewModel : ViewModel() {
    // LiveData holds state which is observed by the UI
    // (state flows down from ViewModel)
//    private val _petList = MutableLiveData(listOf<Pet>())
    var rawList = listOf<Pet>()
    var petList: List<Pet> by mutableStateOf(listOf())
        private set
    var petGroup: Map<String, List<Pet>> by mutableStateOf(HashMap())
        private set


    @Composable
    fun CreateList() {
        rawList = generatePetList()
        petList = rawList
    }

    fun getPet(petId: Int) : Pet{
        return petList[petId]
    }

    // onNameChanged is an event we're defining that the UI can invoke
    // (events flow up from UI)
    fun sortList(sortType: String) {
        when (sortType) {
            AGE -> {
                petList = rawList.sortedBy { it.age }
                petGroup = petList.groupBy { it.age.toString() }
            }
            NAME -> {
                petList = rawList.sortedBy { it.name }
                petGroup = petList.groupBy { it.name.substring(0, 1) }
            }
        }
    }

    fun filterList(sexType: Int, speciesType: Int) {
        petList = when (sexType) {
            MALE, FEMALE -> {
                rawList.filter { it.sex == sexType }
            }
            else -> {
                rawList
            }
        }
        petList = when (speciesType) {
            CAT, DOG -> {
                petList.filter { it.species == speciesType }
            }
            else -> {
                petList
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