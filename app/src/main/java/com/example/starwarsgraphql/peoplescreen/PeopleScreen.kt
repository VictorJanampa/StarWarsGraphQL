package com.example.starwarsgraphql.peoplescreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.domain.models.Person
import com.example.starwarsgraphql.composables.PersonCell
import com.example.starwarsgraphql.composables.TopBar
import com.example.starwarsgraphql.navigation.Screen

@Composable
fun PersonItem(person: Person, navController: NavController) {
    PersonCell(
        name = person.name,
        species = person.species,
        homeworld = person.homeworldName,
        modifier = Modifier.clickable{navController.navigate(Screen.DetailScreen.withArgs(person.id))}
    )
}

@Composable
fun PeopleList(itemList: List<Person>, navController: NavController) {
    LazyColumn(
        state = rememberLazyListState(),
    ) {
        items(itemList) { person ->
            PersonItem(person = person, navController = navController )
        }
    }
}

@Composable
fun PeopleScreen (
    viewModel: PeopleScreenViewModel = hiltViewModel(),
    navController: NavHostController,
){
        PeopleScreenContent(state = viewModel.peopleScreenState, navController = navController)
}

@Composable
fun PeopleScreenContent(state: PeopleScreenUiState, navController: NavHostController) {
    val people by state.peopleListState.collectAsState()
    Column {
        TopBar(title = "People")
        PeopleList(itemList = people, navController = navController )
    }
}

