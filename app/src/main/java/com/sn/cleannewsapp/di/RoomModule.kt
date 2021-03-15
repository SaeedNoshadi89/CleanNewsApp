package com.sn.cleannewsapp.di

import android.content.Context
import androidx.room.Room
import com.sn.cleannewsapp.data.cache.NewsDatabase
import com.sn.cleannewsapp.utils.Constant.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

/*    @Singleton
    @Provides
    fun provideNewsDatabase(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(appContext, NewsDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideNewsDao(db: NewsDatabase) = db.newsDao()*/
}