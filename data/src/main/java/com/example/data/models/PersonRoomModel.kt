package com.example.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.di.PEOPLE_TABLE_NAME

@Entity(tableName = PEOPLE_TABLE_NAME)
data class PersonRoomModel(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val species: String,
    val homeworldName: String,
    val isFavorite: Boolean = false
)