package com.example.starwarsgraphql.detailscreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.PeopleRepositoryImpl
import com.example.domain.models.PersonDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val repository: PeopleRepositoryImpl,
    savedStateHandle: SavedStateHandle
    ) : ViewModel() {
    private val personId: String? = savedStateHandle.get<String>("personId")
    private var loadingState: MutableStateFlow<LoadingState> = MutableStateFlow(LoadingState.LOADING)
    private var person: MutableStateFlow<PersonDetails> = MutableStateFlow(PersonDetails(
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
            person.value = repository.getDetailsPerson(id).apply {
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