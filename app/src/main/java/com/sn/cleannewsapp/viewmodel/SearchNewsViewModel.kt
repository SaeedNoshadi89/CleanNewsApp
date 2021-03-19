package com.sn.cleannewsapp.viewmodel

import androidx.lifecycle.ViewModel
import com.sn.cleannewsapp.data.entities.NewsResponse
import com.sn.cleannewsapp.repository.SearchNewsRepository
import com.sn.cleannewsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class SearchNewsViewModel @Inject constructor(private val searchNewsRepository: SearchNewsRepository) :
    ViewModel() {

    fun getSearchNews(query: String): Flow<Resource<NewsResponse, String>> = flow {
        searchNewsRepository.searchNews(query).collect {
            emit(it)
        }
    }.flowOn(Dispatchers.IO)
}