package com.example.starwarsgraphql.peoplescreen

import androidx.compose.foundation.lazy.LazyListState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import com.example.data.models.PersonRoomModel
import com.example.domain.models.Person
import com.example.starwarsgraphql.common.LoadingState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

data class PeopleScreenUiState(
    val loadingState: StateFlow<LoadingState>,
    val peopleListState: Flow<PagingData<PersonRoomModel>>,
    val onFavoritePressed: (Person) -> Unit,
    val updateLoadingState: (LoadingState) -> Unit

)