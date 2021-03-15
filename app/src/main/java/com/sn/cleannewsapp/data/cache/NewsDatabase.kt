package com.sn.cleannewsapp.data.cache

import androidx.room.RoomDatabase

//@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao

}