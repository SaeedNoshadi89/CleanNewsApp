package com.sn.cleannewsapp.viewmodel

import androidx.lifecycle.ViewModel
import com.sn.cleannewsapp.data.entities.Article
import com.sn.cleannewsapp.repository.SavedNewsRepository
import com.sn.cleannewsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class SavedNewsViewModel @Inject constructor(private val savedNewsRepository: SavedNewsRepository) :
    ViewModel() {

    fun getAllArticles(): Flow<Resource<List<Article>, String>> = flow {
     savedNewsRepository.getAllArticles().collect {
         emit(it)
     }
    }.flowOn(Dispatchers.IO)

    fun deleteArticle(article: Article): Flow<Resource<String, String>> = flow {
        savedNewsRepository.deleteArticle(article).collect {
            emit(it)
        }
    }.flowOn(Dispatchers.IO)
}