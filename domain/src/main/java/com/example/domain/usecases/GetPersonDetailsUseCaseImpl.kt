package com.example.domain.usecases

import com.example.domain.models.PersonDetails
import com.example.domain.repository.PeopleRepository
import javax.inject.Inject

class GetPersonDetailsUseCaseImpl @Inject constructor (private val repository: PeopleRepository) : GetPersonDetailsUseCase {
    override suspend fun invoke(id: String): PersonDetails {
        return repository.getDetailsPerson(id)
    }
}