package com.example.domain.usecases

import com.example.domain.models.Person
import com.example.domain.repository.PeopleRepository
import javax.inject.Inject

class MarkPersonAsFavoriteUseCaseImpl @Inject constructor (private val repository: PeopleRepository) : MarkPersonAsFavoriteUseCase {
    override suspend fun invoke(person: Person) {
        repository.updatePerson(person)
    }
}