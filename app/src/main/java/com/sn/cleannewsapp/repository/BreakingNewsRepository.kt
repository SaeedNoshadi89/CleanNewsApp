package com.sn.cleannewsapp.repository

import com.sn.cleannewsapp.data.cache.NewsDao
import com.sn.cleannewsapp.data.remote.NewsApi
import javax.inject.Inject

class BreakingNewsRepository @Inject constructor(private val newsApi: NewsApi, private val newsDao: NewsDao) {

}