package com.example.domain.usecases

import com.example.domain.models.Person
import com.example.domain.repository.PeopleRepository

interface GetPeopleUseCase{
    suspend operator fun invoke(): List<Person>
}