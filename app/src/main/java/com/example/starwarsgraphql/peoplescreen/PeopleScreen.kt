package com.example.starwarsgraphql.peoplescreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.data.models.PersonMapper
import com.example.domain.models.Person
import com.example.starwarsgraphql.common.LoadingState
import com.example.starwarsgraphql.composables.*
import com.example.starwarsgraphql.navigation.Screen
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun PersonItem(person: Person, navController: NavController, onFavoritePressed: (Person) -> Unit) {
    PersonCell(
        name = person.name,
        species = person.species,
        homeworld = person.homeworldName,
        isFavorite = person.isFavorite,
        onFavoritePressed = {onFavoritePressed(person)},
        onNavigatePressed = { navController.navigate(Screen.DetailScreen.withArgs(person.id)) }
    )
}

@Composable
fun PeopleList(itemList: List<Person>, navController: NavController, loadingState: LoadingState) {
    when(loadingState){
        LoadingState.LOADING -> LoadingCell()
        LoadingState.ERROR -> NoticeCell()
        LoadingState.COMPLETED -> LazyColumn(
            state = rememberLazyListState(),
        ) {
            items(itemList) { person ->
                PersonItem(
                    person = person,
                    navController = navController,
                    onFavoritePressed = {}
                )
            }
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
    val peopleOfStarWars = state.peopleListState.collectAsLazyPagingItems()

    val swipeRefreshState = rememberSwipeRefreshState(false)

    Column {
        TopBar(title = "People Of Star Wars")
        Box {
            peopleOfStarWars.apply {
                SwipeRefresh(
                    state = swipeRefreshState,
                    onRefresh = {
                        peopleOfStarWars.refresh()
                    },
                    swipeEnabled = loadState.refresh !is LoadState.Loading
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        items(items = peopleOfStarWars) { person ->
                            PersonItem(
                                person = PersonMapper.toPersonDomainModel(person),
                                navController = navController,
                                onFavoritePressed = state.onFavoritePressed
                            )
                        }
                        if (loadState.append is LoadState.Loading) {
                            item { LoadingCell() }
                        }
                    }
                }
                if (loadState.refresh is LoadState.Loading) {
                    LoadingCell()
                } else if (peopleOfStarWars.itemCount == 0) {
                    NoticeCell()
                }
            }
        }
        //PeopleList(itemList = people, navController = navController, loadingState = loadingState)
    }
}


