package com.example.starwarsgraphql.detailscreen

import com.example.domain.models.PersonDetails
import kotlinx.coroutines.flow.StateFlow

data class DetailScreenUiState(
    val loadingState: StateFlow<LoadingState>,
    val personState: StateFlow<PersonDetails>,
    val getPersonDetails: (String) -> Unit
)