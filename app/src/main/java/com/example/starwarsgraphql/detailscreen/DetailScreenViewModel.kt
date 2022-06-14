package com.example.starwarsgraphql.detailscreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.PeopleRepositoryImpl
import com.example.domain.models.PersonDetails
import com.example.domain.usecases.GetPersonDetailsUseCase
import com.example.domain.usecases.GetPersonDetailsUseCaseImpl
import com.example.starwarsgraphql.common.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val getPersonDetailsUseCase: GetPersonDetailsUseCaseImpl,
    savedStateHandle: SavedStateHandle
    ) : ViewModel() {
    private val personId: String? = savedStateHandle.get<String>("personId")
    private val loadingState: MutableStateFlow<LoadingState> = MutableStateFlow(LoadingState.LOADING)
    private val person: MutableStateFlow<PersonDetails> = MutableStateFlow(PersonDetails(
        name = "",
        hairColor = "",
        eyeColor = "",
        skinColor = "",
        birthYear = "",
        vehicles = null)
    )

    init {
        getPersonDetails(personId ?: "")
    }

    private fun getPersonDetails(id: String){
        viewModelScope.launch {
            person.value = getPersonDetailsUseCase.invoke(id).apply {
                if(this.name == "") loadingState.value = LoadingState.ERROR
                else loadingState.value = LoadingState.COMPLETED
            }
        }
    }

    val state = DetailScreenUiState(
        loadingState = loadingState,
        personState = person,
        getPersonDetails = this::getPersonDetails
    )
}