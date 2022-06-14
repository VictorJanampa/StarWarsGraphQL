package com.example.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.di.PEOPLE_REMOTE_KEYS_TABLE_NAME
import com.example.data.di.PEOPLE_TABLE_NAME


@Entity(tableName = PEOPLE_REMOTE_KEYS_TABLE_NAME)
data class PeopleRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val nextKey: String?
)