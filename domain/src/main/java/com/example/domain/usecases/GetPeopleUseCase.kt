package com.example.domain.usecases

import androidx.paging.PagingData
import com.example.domain.models.Person
import com.example.domain.repository.PeopleRepository
import kotlinx.coroutines.flow.Flow

interface GetPeopleUseCase{
    operator fun <T:Any> invoke(): Flow<PagingData<T>>
}