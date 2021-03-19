package com.sn.cleannewsapp.repository

import com.sn.cleannewsapp.data.entities.Article
import com.sn.cleannewsapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ArticleRepositoryDataSource {

    suspend fun insertArticle(article: Article): Flow<Resource<Long, String>>

}