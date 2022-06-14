package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.models.PeopleRemoteKeys
import com.example.data.models.PersonRoomModel

@Database(entities = [PersonRoomModel::class, PeopleRemoteKeys::class], version = 1, exportSchema = false)
abstract class PeopleDatabase : RoomDatabase() {
    abstract fun peopleDatabaseDao(): PeopleDatabaseDao
    abstract fun peopleRemoteKeysDao(): PeopleRemoteKeysDao
}