package com.sn.cleannewsapp.repository

import com.sn.cleannewsapp.data.entities.NewsResponse
import com.sn.cleannewsapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface SearchNewsRepositoryDataSource {
    fun searchNews(query: String): Flow<Resource<NewsResponse, String>>
}