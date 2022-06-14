package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.di.PEOPLE_REMOTE_KEYS_TABLE_NAME
import com.example.data.models.PeopleRemoteKeys

@Dao
interface PeopleRemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(remoteKey: List<PeopleRemoteKeys>)

    @Query("SELECT * FROM $PEOPLE_REMOTE_KEYS_TABLE_NAME WHERE id = :id")
    suspend fun remoteKeysPersonId(id: String): PeopleRemoteKeys?

    @Query("DELETE FROM $PEOPLE_REMOTE_KEYS_TABLE_NAME")
    suspend fun clearRemoteKeys()
}