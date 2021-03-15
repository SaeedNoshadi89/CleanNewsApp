package com.sn.cleannewsapp.repository

import com.sn.cleannewsapp.data.cache.NewsDao
import com.sn.cleannewsapp.data.entities.NewsResponse
import com.sn.cleannewsapp.data.remote.NewsApi
import com.sn.cleannewsapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/*
class BreakingNewsRepository @Inject constructor(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao
) : BreakingRepositoryDataSource {

    override suspend fun getBreakingNews(
        countryCode: String,
        pageNumber: Int
    ): Flow<Resource<NewsResponse>> = flow {
        newsApi.getBreakingNews(countryCode = countryCode, pageNumber = pageNumber).collect {
            emit(it)
        }
    }.catch {
        emit(Resource.Error("error server"))
    }.flowOn(Dispatchers.IO)

}*/
