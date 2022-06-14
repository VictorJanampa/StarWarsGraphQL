package com.example.domain.usecases

import androidx.paging.PagingData
import com.example.domain.repository.PeopleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPeopleUseCaseImpl @Inject constructor(private val repository: PeopleRepository):GetPeopleUseCase {
    override fun <T : Any> invoke(): Flow<PagingData<T>> {
        return repository.getPagingData()
    }

}