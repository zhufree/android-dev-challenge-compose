package com.example.androiddevchallenge

import com.example.androiddevchallenge.PetConstants.locations

data class Pet(
    val name: String,
    val species: Int,
    val age: Int,
    val sex: Int,
    val headUrl: String
) {
    fun showAge(): String {
        return "Age: $age"
    }

    fun showSpecies(): String {
        return when (species) {
            PetConstants.Species.CAT -> {
                "\uD83D\uDC31"
            }
            PetConstants.Species.DOG -> {
                "\uD83D\uDC36"
            }
            else -> {
                "\uD83D\uDC36"
            }
        }
    }

    fun showSex(): String {
        return when (sex) {
            PetConstants.Sex.FEMALE -> {
                "Female"
            }
            PetConstants.Sex.MALE -> {
                "Male"
            }
            else -> {
                "Female"
            }
        }
    }

    fun showDetail(): String {
        val desc1 = listOf("cute", "smart", "clever", "sturdy", "calm").random()
        val location = locations.random()
        val toy = listOf("ball", "box", "wool", "plate").random()
        return "$name, A $desc1 ${showSpecies()}, born in $location, $age years old now, " +
                "like to play with $toy, waiting for a new owner~"
    }
}
