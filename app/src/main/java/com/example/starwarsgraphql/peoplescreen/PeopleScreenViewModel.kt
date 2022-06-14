package com.example.starwarsgraphql.peoplescreen

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.data.models.PersonMapper
import com.example.data.models.PersonRoomModel
import com.example.domain.models.Person
import com.example.data.repository.PeopleRepositoryImpl
import com.example.starwarsgraphql.common.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleScreenViewModel @Inject constructor(
    private val repository: PeopleRepositoryImpl
    ) : ViewModel() {

    private val loadingState: MutableStateFlow<LoadingState> = MutableStateFlow(LoadingState.LOADING)
    private val peopleListFlow: Flow<PagingData<PersonRoomModel>> = repository.getPagingData<PersonRoomModel>().cachedIn(viewModelScope)

    private fun onFavoritePressed(person: Person){
        viewModelScope.launch {
            repository.updatePerson(person)
        }
    }

    private fun updateLoadingState(state: LoadingState){
        viewModelScope.launch {
            loadingState.value = state
        }
    }

    val peopleScreenState = PeopleScreenUiState(
        peopleListState = peopleListFlow,
        loadingState = loadingState,
        onFavoritePressed = this::onFavoritePressed,
        updateLoadingState = this::updateLoadingState
    )
}