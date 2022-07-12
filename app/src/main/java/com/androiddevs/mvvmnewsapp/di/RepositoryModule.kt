package com.androiddevs.mvvmnewsapp.di

import com.androiddevs.mvvmnewsapp.data.local.room.NewsDao
import com.androiddevs.mvvmnewsapp.data.remote.NewsApi
import com.androiddevs.mvvmnewsapp.data.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsRepository(dao: NewsDao, api: NewsApi): NewsRepository {
        return NewsRepository(dao, api)
    }
}