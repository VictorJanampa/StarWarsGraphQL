package com.example.starwarsgraphql.peoplescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.data.models.PersonRoomModel
import com.example.domain.models.Person
import com.example.domain.usecases.GetPeopleUseCaseImpl
import com.example.domain.usecases.MarkPersonAsFavoriteUseCaseImpl
import com.example.starwarsgraphql.common.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleScreenViewModel @Inject constructor(
    getPeopleUseCase: GetPeopleUseCaseImpl,
    private val markPersonAsFavoriteUseCase: MarkPersonAsFavoriteUseCaseImpl,

    ) : ViewModel() {

    private val loadingState: MutableStateFlow<LoadingState> = MutableStateFlow(LoadingState.LOADING)
    private val peopleListFlow: Flow<PagingData<PersonRoomModel>> = getPeopleUseCase.invoke<PersonRoomModel>().cachedIn(viewModelScope)

    private fun onFavoritePressed(person: Person){
        viewModelScope.launch {
            markPersonAsFavoriteUseCase.invoke(person)
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