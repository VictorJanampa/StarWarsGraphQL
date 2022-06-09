package com.example.domain.models

data class PersonDetails(
    val name: String,
    val hairColor: String,
    val eyeColor: String,
    val skinColor: String,
    val birthYear: String,
    val vehicles: List<String?>?
)