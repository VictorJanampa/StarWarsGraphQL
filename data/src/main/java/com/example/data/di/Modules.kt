package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.apollographql.apollo3.ApolloClient
import com.example.data.database.PeopleDatabase
import com.example.data.database.PeopleDatabaseDao
import com.example.data.database.PeopleRemoteKeysDao
import com.example.data.repository.PeopleRepositoryImpl
import com.example.domain.repository.PeopleRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideApi(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(BASE_URL)
            .build()
    }
}
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDao(peopleDatabase: PeopleDatabase): PeopleDatabaseDao {
        return peopleDatabase.peopleDatabaseDao()
    }

    @Provides
    fun provideRemoteKeysDao(peopleDatabase: PeopleDatabase): PeopleRemoteKeysDao {
        return peopleDatabase.peopleRemoteKeysDao()
    }

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): PeopleDatabase {
        return Room.databaseBuilder(context, PeopleDatabase::class.java, PEOPLE_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

}


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{
    @Binds
    abstract fun bindRepository(impl: PeopleRepositoryImpl): PeopleRepository
}
