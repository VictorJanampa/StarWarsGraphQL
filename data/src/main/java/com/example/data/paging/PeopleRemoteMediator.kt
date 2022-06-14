package com.example.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.exception.ApolloException
import com.apollographql.apollo3.exception.ApolloHttpException
import com.example.data.LoadPeopleByPageQuery
import com.example.data.database.PeopleDatabase
import com.example.data.models.PeopleRemoteKeys
import com.example.data.models.PersonMapper
import com.example.data.models.PersonRoomModel
import com.example.domain.models.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ExperimentalPagingApi
class PeopleRemoteMediator @Inject constructor(
    private val database: PeopleDatabase,
    private val apolloClient: ApolloClient
) : RemoteMediator<Int, PersonRoomModel>() {

    override suspend fun initialize(): InitializeAction {
        val rowsCount = database.peopleDatabaseDao().getRowsCount()
        return if (rowsCount > 0) InitializeAction.SKIP_INITIAL_REFRESH else InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PersonRoomModel>
    ): MediatorResult {
        val peopleDao = database.peopleDatabaseDao()
        val remoteKeysDao = database.peopleRemoteKeysDao()

        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextKey = remoteKeys?.nextKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    nextKey
                }
            }

            var people: List<Person>
            val hasNextPage: Boolean
            val cursor: String
            val id: String

            withContext(Dispatchers.IO) {
                val response = try {
                    apolloClient.query(LoadPeopleByPageQuery(
                        after = Optional.Present(loadKey),
                        first = Optional.Present(5))
                    ).execute()
                } catch (e: ApolloException) {
                    null
                }
                people = response?.data?.allPeople?.people?.filterNotNull()?.map {
                    PersonMapper.toPersonDomainModel(it)
                } ?: emptyList()
                hasNextPage = response?.data?.allPeople?.pageInfo?.hasNextPage ?: false
                cursor = response?.data?.allPeople?.pageInfo?.endCursor ?: ""
                id = response?.data?.allPeople?.people?.last()?.id ?: ""
            }

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeysDao.clearRemoteKeys()
                    peopleDao.clearPeople()
                }

                val keys = PeopleRemoteKeys(
                    id = id,
                    nextKey = cursor
                )

                remoteKeysDao.insertAll(listOf(keys))
                peopleDao.insertPeople(people.map { PersonMapper.toPersonRoomModel(it)})
            }

            MediatorResult.Success(endOfPaginationReached = !hasNextPage)
        } catch (e: Exception) {
            println("Andrio : ${e.message}")
            MediatorResult.Error(e)
        } catch (e: ApolloHttpException) {
            println("Andrio : ${e.message}")
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, PersonRoomModel>): PeopleRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { person -> database.peopleRemoteKeysDao().remoteKeysPersonId(person.id) }
    }
}

