package com.sn.cleannewsapp.repository

import com.sn.cleannewsapp.data.entities.Article
import com.sn.cleannewsapp.utils.Resource
import kotlinx.coroutines.flow.Flow


interface SavedNewsRepositoryDataSource {
    fun getAllArticles(): Flow<Resource<List<Article>, String>>
    fun deleteArticle(article: Article): Flow<Resource<String, String>>
}