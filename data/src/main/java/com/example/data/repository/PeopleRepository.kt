package com.example.data.repository

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
import com.example.LoadPeopleQuery
import com.example.data.models.Person
import com.example.data.models.PersonMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PeopleRepository(apolloClient: ApolloClient) {

    fun getAllPeople(apolloClient: ApolloClient): List<Person> {
        var people: List<Person> = emptyList()
        CoroutineScope(Dispatchers.IO).launch {
            val response = try {
                apolloClient.query(LoadPeopleQuery()).execute()
            } catch (e: ApolloException) {
                Log.i("onGetAllPeople", e.message ?: "null")
                null
            }
            people = response?.data?.allPeople?.people?.filterNotNull()?.map { PersonMapper.toPerson(it) }
                ?: emptyList()
        }
        return people
    }
}