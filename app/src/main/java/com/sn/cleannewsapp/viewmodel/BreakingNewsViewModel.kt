package com.sn.cleannewsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sn.cleannewsapp.data.entities.NewsResponse
import com.sn.cleannewsapp.repository.BreakingNewsRepository
import com.sn.cleannewsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreakingNewsViewModel @Inject constructor(private val breakingNewsRepository: BreakingNewsRepository) :
    ViewModel() {

    fun getBreakingNews(countryCode: String, pageNumber: Int): Flow<Resource<NewsResponse>> = flow {
        viewModelScope.launch {
            breakingNewsRepository.getBreakingNews(
                countryCode = countryCode,
                pageNumber = pageNumber
            ).collect {
                emit(it)
            }
        }
    }.flowOn(Dispatchers.IO)

}