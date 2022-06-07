package com.example.starwarsgraphql.peoplescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.models.Person
import com.example.starwarsgraphql.composables.PersonCell
import com.example.starwarsgraphql.composables.TopBar

@Composable
fun PersonItem(person: Person) {
    PersonCell(
        name = person.name,
        species = person.species,
        homeworld = person.homeworldName
    )
}

@Composable
fun PeopleList(itemList: List<Person>) {
    LazyColumn(
        state = rememberLazyListState(),
    ) {
        items(itemList) { person ->
            PersonItem(person = person)
        }
    }
}

@Composable
fun PeopleScreen (
    viewModel: PeopleScreenViewModel = hiltViewModel(),
){
        PeopleScreenContent(state = viewModel.peopleScreenState)
}

@Composable
fun PeopleScreenContent(state: PeopleScreenUiState) {
    val people by state.peopleListState.collectAsState()
    Column {
        TopBar(title = "People")
        PeopleList(itemList = people)
    }
}

