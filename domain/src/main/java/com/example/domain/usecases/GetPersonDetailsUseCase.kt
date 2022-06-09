package com.example.domain.usecases

import com.example.domain.models.PersonDetails

interface GetPersonDetailsUseCase {
    suspend operator fun invoke(id: String): PersonDetails
}