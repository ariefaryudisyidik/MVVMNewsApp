package com.androiddevs.mvvmnewsapp.di

import com.androiddevs.mvvmnewsapp.data.local.room.NewsDatabase
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
    fun provideNewsRepository(db: NewsDatabase) =
        NewsRepository(db)
}