package com.example.data.models

import com.example.data.LoadPeopleByPageQuery
import com.example.data.LoadPeopleQuery
import com.example.data.PeopleDetailsQuery
import com.example.domain.models.Person
import com.example.domain.models.PersonDetails


class PersonMapper {
    companion object{
        fun<T> toPersonDomainModel(person: T): Person {
            return when(person){
                is LoadPeopleQuery.Person -> Person(
                    id = person.id,
                    name = person.name ?: "",
                    species = person.species?.name ?: "Human" ,
                    homeworldName = person.homeworld?.name ?: "",
                    isFavorite = false
                )
                is LoadPeopleByPageQuery.Person -> Person(
                    id = person.id,
                    name = person.name ?: "",
                    species = person.species?.name ?: "Human" ,
                    homeworldName = person.homeworld?.name ?: "",
                    isFavorite = false
                )
                is PersonRoomModel -> Person(
                    id = person.id,
                    name = person.name ,
                    species = person.species ,
                    homeworldName = person.homeworldName,
                    isFavorite = person.isFavorite
                )
                else -> Person(
                    id = "",
                    name =  "",
                    species = "" ,
                    homeworldName = "",
                    isFavorite = false
                )
            }

        }

        fun <T>toPersonRoomModel(person: T): PersonRoomModel {
            return when (person){
                is LoadPeopleQuery.Person -> PersonRoomModel(
                    id = person.id,
                    name = person.name ?: "",
                    species = person.species?.name ?: "Human" ,
                    homeworldName = person.homeworld?.name ?: "",
                    isFavorite = false
                )
                is LoadPeopleByPageQuery.Person -> PersonRoomModel(
                    id = person.id,
                    name = person.name ?: "",
                    species = person.species?.name ?: "Human" ,
                    homeworldName = person.homeworld?.name ?: "",
                    isFavorite = false
                )
                is Person -> PersonRoomModel(
                    id = person.id,
                    name = person.name,
                    species = person.species,
                    homeworldName = person.homeworldName,
                    isFavorite = person.isFavorite
                )
                else -> PersonRoomModel(
                    id = "",
                    name =  "",
                    species = "" ,
                    homeworldName = "",
                    isFavorite = false
                )
            }



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