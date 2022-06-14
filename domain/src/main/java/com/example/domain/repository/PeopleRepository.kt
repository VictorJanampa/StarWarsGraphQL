package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.models.Person
import com.example.domain.models.PersonDetails
import kotlinx.coroutines.flow.Flow


interface PeopleRepository {
    fun <T : Any>getPagingData(): Flow<PagingData<T>>
    suspend fun getAllPeople(): List<Person>
    suspend fun getDetailsPerson(id: String): PersonDetails
    suspend fun updatePerson(person: Person)
}