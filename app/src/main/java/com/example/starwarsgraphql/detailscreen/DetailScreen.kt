package com.example.starwarsgraphql.detailscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.models.PersonDetails
import com.example.starwarsgraphql.common.LoadingState
import com.example.starwarsgraphql.composables.*

@Composable
fun DetailScreen(
    viewModel: DetailScreenViewModel = hiltViewModel()
) {
    DetailScreenContent(state = viewModel.state)
}

@Composable
fun DetailScreenContent(state: DetailScreenUiState) {

    val person by state.personState.collectAsState()
    val loadingState by state.loadingState.collectAsState()

    Column {
        TopBar(title = person.name)
        PersonDetailsList(person = person, loadingState = loadingState)
    }
}

@Composable
fun PersonDetailsList(person: PersonDetails, loadingState: LoadingState) {
    when(loadingState){
        LoadingState.LOADING -> LoadingCell()
        LoadingState.ERROR -> NoticeCell()
        LoadingState.COMPLETED -> PersonData(person = person)
    }
}

@Composable
fun PersonData(person: PersonDetails) {
    Column{
        SectionHeader(title = "General Information")
        DataCell(trait = "Eye Color" , dataTrait = person.eyeColor)
        DataCell(trait = "Hair Color" , dataTrait = person.hairColor)
        DataCell(trait = "Skin Color" , dataTrait = person.skinColor)
        DataCell(trait = "Birth Color" , dataTrait = person.birthYear)
        if (person.vehicles != null) {
            Column{
                SectionHeader(title = "Vehicles")
                if (person.vehicles.isNullOrEmpty()) VehicleCell("No vehicles")
                else person.vehicles?.forEach { vehicle -> vehicle?.let { VehicleCell(it) } }
            }
        }
    }
}
