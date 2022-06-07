package com.example.starwarsgraphql.peoplescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Person
import com.example.data.repository.PeopleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleScreenViewModel @Inject constructor(
    repository: PeopleRepository
    ) : ViewModel() {
    private var peopleList: MutableStateFlow<List<Person>> = MutableStateFlow(emptyList())

    init {
        viewModelScope.launch {
            peopleList.value = repository.getAllPeople()
        }
    }

    val peopleScreenState = PeopleScreenUiState(
        peopleListState = peopleList
    )

    }