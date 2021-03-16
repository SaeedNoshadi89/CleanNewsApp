package com.sn.cleannewsapp.repository

import com.sn.cleannewsapp.data.entities.NewsResponse
import com.sn.cleannewsapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface BreakingRepositoryDataSource {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int):  Flow<Resource<NewsResponse, String>>

}