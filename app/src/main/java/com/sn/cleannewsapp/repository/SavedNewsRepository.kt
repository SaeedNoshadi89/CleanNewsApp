package com.sn.cleannewsapp.repository

import com.sn.cleannewsapp.data.cache.NewsDao
import com.sn.cleannewsapp.data.entities.Article
import com.sn.cleannewsapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SavedNewsRepository @Inject constructor(private val newsDao: NewsDao): SavedNewsRepositoryDataSource {
    override fun getAllArticles(): Flow<Resource<List<Article>, String>> = flow {
        newsDao.getAllArticle().collect {
           try {
               emit(Resource.success<List<Article>, String>(it))
           }catch (e: Exception){
               emit(Resource.error<List<Article>, String>(message = e.message.toString()))
           }
        }
    }.flowOn(Dispatchers.IO)

    override fun deleteArticle(article: Article): Flow<Resource<String, String>> = flow {
        try {
            newsDao.deleteArticle(article)
            emit(Resource.success<String, String>())
        }catch (e: Exception){
            emit(Resource.error<String, String>(message = e.message.toString()))
        }

    }.flowOn(Dispatchers.IO)
}