package com.example.data.models

import com.example.data.LoadPeopleQuery
import com.example.data.PeopleDetailsQuery
import com.example.domain.models.Person
import com.example.domain.models.PersonDetails


class PersonMapper {
    companion object{
        fun toPersonDomainModel(person: LoadPeopleQuery.Person): Person {
            return Person(
                id = person.id,
                name = person.name ?: "",
                species = person.species?.name ?: "Human" ,
                homeworldName = person.homeworld?.name ?: "",
            )
        }

        fun toPersonRoomModel(person: LoadPeopleQuery.Person): PersonRoomModel {
            return PersonRoomModel(
                id = person.id,
                name = person.name ?: "",
                species = person.species?.name ?: "Human" ,
                homeworldName = person.homeworld?.name ?: "",
            )
        }
        fun toPersonDetailsDomainModel(person: PeopleDetailsQuery.Person?): PersonDetails{
            return PersonDetails(
                name = person?.name ?: "",
                hairColor = person?.hairColor ?: "",
                eyeColor = person?.eyeColor ?: "",
                skinColor = person?.skinColor ?: "",
                birthYear = person?.birthYear ?: "",
                vehicles = person?.vehicleConnection?.edges?.map { it?.node?.name }
            )
        }
    }
}