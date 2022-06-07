package com.example.starwarsgraphql.peoplescreen

import com.example.domain.models.Person
import kotlinx.coroutines.flow.StateFlow

data class PeopleScreenUiState(
    val peopleListState: StateFlow<List<Person>>
)