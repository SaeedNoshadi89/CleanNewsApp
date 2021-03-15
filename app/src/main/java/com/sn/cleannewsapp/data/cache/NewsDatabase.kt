package com.sn.cleannewsapp.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sn.cleannewsapp.data.entities.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao

}