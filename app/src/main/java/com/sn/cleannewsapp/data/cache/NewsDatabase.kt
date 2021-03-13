package com.sn.cleannewsapp.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sn.cleannewsapp.data.entities.NewsEntity

@Database(entities = [NewsEntity::class], version = 1, exportSchema = false)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao

}