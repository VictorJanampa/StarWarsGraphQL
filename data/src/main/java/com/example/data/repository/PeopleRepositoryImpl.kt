package com.example.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
import com.example.data.LoadPeopleQuery
import com.example.data.PeopleDetailsQuery
import com.example.data.database.PeopleDatabaseDao
import com.example.domain.models.Person
import com.example.data.models.PersonMapper
import com.example.data.paging.PeopleRemoteMediator
import com.example.domain.models.PersonDetails
import com.example.domain.repository.PeopleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OptIn(ExperimentalPagingApi::class)
class PeopleRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient,
    private val database: PeopleDatabaseDao,
    private val remoteMediator: PeopleRemoteMediator
    ) :
    PeopleRepository {
    override fun <T : Any> getPagingData(): Flow<PagingData<T>> {
        val pagingSourceFactory = { database.getAllPeople()}
        return Pager(
            config = PagingConfig(pageSize = 5, enablePlaceholders = false),
            remoteMediator = remoteMediator,
            pagingSourceFactory = pagingSourceFactory
        ).flow as Flow<PagingData<T>>
    }

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

    override suspend fun updatePerson(person: Person) {
        val updatedPerson = PersonMapper.toPersonRoomModel(person.copy(isFavorite = !person.isFavorite))
        withContext(Dispatchers.IO){
            database.updatePerson(updatedPerson)
        }
    }
}