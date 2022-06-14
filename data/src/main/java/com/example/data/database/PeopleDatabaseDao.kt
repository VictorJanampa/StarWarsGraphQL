package com.example.data.database

import androidx.paging.PagingSource
import androidx.room.*
import com.example.data.di.PEOPLE_TABLE_NAME
import com.example.data.models.PersonRoomModel

@Dao
interface PeopleDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPerson(person: PersonRoomModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPeople(people: List<PersonRoomModel>)

    @Update
    suspend fun updatePerson(person: PersonRoomModel)

    @Query("SELECT * from $PEOPLE_TABLE_NAME")
    fun getAllPeople(): PagingSource<Int, PersonRoomModel>

    @Query("DELETE FROM $PEOPLE_TABLE_NAME")
    suspend fun clearPeople()

    @Query("SELECT count(*) FROM $PEOPLE_TABLE_NAME")
    suspend fun getRowsCount(): Int
}
