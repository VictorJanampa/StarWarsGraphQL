package com.example.data.models

import com.example.LoadPeopleQuery

class PersonMapper {
    companion object{
        fun toPerson(person : LoadPeopleQuery.Person): Person {
            return Person(
                id = person.id,
                name = person.name ?: "",
                species = (person.species ?: "Human") as String,
                homeworldName = person.homeworld?.name ?: "",
            )
        }
    }
}