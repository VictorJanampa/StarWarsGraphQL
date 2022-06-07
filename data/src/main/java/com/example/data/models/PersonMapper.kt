package com.example.data.models

import com.example.data.LoadPeopleQuery
import com.example.domain.models.Person


class PersonMapper {
    companion object{
        fun toPersonDomainModel(person : LoadPeopleQuery.Person): Person {
            return Person(
                id = person.id,
                name = person.name ?: "",
                species = person.species?.name ?: "Human" ,
                homeworldName = person.homeworld?.name ?: "",
            )
        }

        fun toPersonRoomModel(person : LoadPeopleQuery.Person): PersonRoomModel {
            return PersonRoomModel(
                id = person.id,
                name = person.name ?: "",
                species = person.species?.name ?: "Human" ,
                homeworldName = person.homeworld?.name ?: "",
            )
        }
    }
}