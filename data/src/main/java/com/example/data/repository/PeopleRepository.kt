package com.example.data.repository

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
import com.example.data.LoadPeopleQuery
import com.example.domain.models.Person
import com.example.data.models.PersonMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleRepository @Inject constructor(private val apolloClient: ApolloClient) {

    suspend fun getAllPeople(): List<Person> {
        var people: List<Person>
        withContext(Dispatchers.IO) {
            val response = try {
                Log.i("Andrio", "Success Query")
                apolloClient.query(LoadPeopleQuery()).execute()
            } catch (e: ApolloException) {
                Log.i("Andrio", e.message ?: "null")
                null
            }
            people = response?.data?.allPeople?.people?.filterNotNull()?.map {
                PersonMapper.toPersonDomainModel(it)
            } ?: emptyList()
        }
        println("Andrio :$people")
        return people
    }
}