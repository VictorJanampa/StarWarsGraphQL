package com.example.domain.repository

import com.example.domain.models.Person
import com.example.domain.models.PersonDetails

interface PeopleRepository {
    suspend fun getAllPeople(): List<Person>
    suspend fun getDetailsPerson(id: String): PersonDetails
}