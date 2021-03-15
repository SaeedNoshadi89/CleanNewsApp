package com.sn.cleannewsapp.di

import com.sn.cleannewsapp.data.cache.NewsDao
import com.sn.cleannewsapp.data.remote.NewsApi
import com.sn.cleannewsapp.repository.BreakingNewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideBreakingNewsRepository(newsApi: NewsApi, newsDao: NewsDao) =
        BreakingNewsRepository(newsApi, newsDao)

}