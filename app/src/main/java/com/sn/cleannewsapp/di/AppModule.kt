package com.sn.cleannewsapp.di

import com.sn.cleannewsapp.data.cache.NewsDao
import com.sn.cleannewsapp.data.remote.NewsApi
import com.sn.cleannewsapp.repository.ArticleRepository
import com.sn.cleannewsapp.repository.BreakingNewsRepository
import com.sn.cleannewsapp.repository.SavedNewsRepository
import com.sn.cleannewsapp.repository.SearchNewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBreakingNewsRepository(newsApi: NewsApi, newsDao: NewsDao) =
        BreakingNewsRepository(newsApi, newsDao)

    @Provides
    fun provideSavedNewsRepository(newsDao: NewsDao) =
        SavedNewsRepository(newsDao)

    @Provides
    fun provideArticleRepository(newsDao: NewsDao) = ArticleRepository(newsDao)

    @Provides
    fun provideSearchNewsRepository(newsApi: NewsApi) = SearchNewsRepository(newsApi)

}