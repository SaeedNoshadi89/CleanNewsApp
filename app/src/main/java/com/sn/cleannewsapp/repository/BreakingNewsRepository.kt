package com.sn.cleannewsapp.repository

import com.sn.cleannewsapp.data.cache.NewsDao
import com.sn.cleannewsapp.data.entities.Article
import com.sn.cleannewsapp.data.entities.NewsResponse
import com.sn.cleannewsapp.data.remote.NewsApi
import com.sn.cleannewsapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BreakingNewsRepository @Inject constructor(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao
) : BreakingRepositoryDataSource {
    override suspend fun getBreakingNews(
        countryCode: String,
        pageNumber: Int
    ): Flow<Resource<NewsResponse, String>> = flow {
        try {
            newsApi.getBreakingNews(countryCode = countryCode, pageNumber = pageNumber).run {
                emit(Resource.success<NewsResponse, String>(this))
            }
        }catch (e: Exception){
            emit(Resource.error<NewsResponse, String>(message = e.message.toString()))
        }

    }.flowOn(Dispatchers.IO)

    override suspend fun insertArticle(article: Article): Flow<Resource<Long, String>> = flow {
        newsDao.insert(article).run {
            emit(Resource.success<Long, String>(this))
        }
    }.flowOn(Dispatchers.IO)
}