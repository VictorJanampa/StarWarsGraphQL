package com.example.domain.usecases

import com.example.domain.models.Person

interface MarkPersonAsFavoriteUseCase {
    suspend operator fun invoke(person: Person)
}