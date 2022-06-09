package com.example.domain.usecases

import com.example.domain.models.Person
import com.example.domain.repository.PeopleRepository
import javax.inject.Inject

class GetPeopleUseCaseImpl @Inject constructor(private val repository: PeopleRepository):GetPeopleUseCase {
    override suspend fun invoke(): List<Person> {
        return repository.getAllPeople()
    }
}