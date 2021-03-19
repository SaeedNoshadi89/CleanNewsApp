package com.sn.cleannewsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sn.cleannewsapp.data.entities.Article
import com.sn.cleannewsapp.data.entities.NewsResponse
import com.sn.cleannewsapp.repository.BreakingNewsRepository
import com.sn.cleannewsapp.utils.Resource
import com.sn.cleannewsapp.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreakingNewsViewModel @Inject constructor(private val breakingNewsRepository: BreakingNewsRepository) :
    ViewModel() {

    private val breakingNewsStateFlow =
        MutableStateFlow<Resource<NewsResponse?, String?>>(Resource.loading(null))

    init {
        fetchBreakingNews("us", 1)
    }


    fun insertArticle(article: Article): Flow<Resource<Long, String>> = flow {
        breakingNewsRepository.insertArticle(article).collect {
            emit(it)
        }
    }.flowOn(Dispatchers.IO)

    private fun fetchBreakingNews(countryCode: String, pageNumber: Int) {
        viewModelScope.launch {
            breakingNewsRepository.getBreakingNews(
                countryCode = countryCode,
                pageNumber = pageNumber
            ).collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        breakingNewsStateFlow.value = Resource.success(it.data)
                    }
                    Status.ERROR -> {
                        breakingNewsStateFlow.value = Resource.error(message = it.message)
                    }
                    else -> {
                        breakingNewsStateFlow.value = Resource.loading(null)
                    }
                }
            }
        }
    }

    val getBreakingNewsStateFlow: StateFlow<Resource<NewsResponse?, String?>> =
        breakingNewsStateFlow

}