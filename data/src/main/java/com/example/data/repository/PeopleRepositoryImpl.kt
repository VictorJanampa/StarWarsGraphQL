package com.example.data.repository

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
import com.example.data.LoadPeopleQuery
import com.example.data.PeopleDetailsQuery
import com.example.domain.models.Person
import com.example.data.models.PersonMapper
import com.example.domain.models.PersonDetails
import com.example.domain.repository.PeopleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleRepositoryImpl @Inject constructor(private val apolloClient: ApolloClient) :
    PeopleRepository {

    override suspend fun getAllPeople(): List<Person> {
        var people: List<Person>
        withContext(Dispatchers.IO) {
            val response = try {
                apolloClient.query(LoadPeopleQuery()).execute()
            } catch (e: ApolloException) {
                null
            }
            people = response?.data?.allPeople?.people?.filterNotNull()?.map {
                PersonMapper.toPersonDomainModel(it)
            } ?: emptyList()
        }
        return people
    }

    override suspend fun getDetailsPerson(id: String): PersonDetails {
        var personDetails: PersonDetails
        withContext(Dispatchers.IO) {
            val response = try {
                apolloClient.query(PeopleDetailsQuery(id = id)).execute()
            } catch (e: ApolloException) {
                null
            }
            personDetails = PersonMapper.toPersonDetailsDomainModel(response?.data?.person)
        }
        return personDetails
    }


    fun insertPeople(personList: List<Person>) {
        TODO("Not yet implemented")
    }

}