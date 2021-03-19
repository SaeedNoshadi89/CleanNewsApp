package com.sn.cleannewsapp.repository

import com.sn.cleannewsapp.data.cache.NewsDao
import com.sn.cleannewsapp.data.entities.Article
import com.sn.cleannewsapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ArticleRepository @Inject constructor(private val newsDao: NewsDao): ArticleRepositoryDataSource {
    override suspend fun insertArticle(article: Article): Flow<Resource<Long, String>> = flow {
        newsDao.insert(article).run {
            emit(Resource.success<Long, String>(this))
        }
    }.flowOn(Dispatchers.IO)

}