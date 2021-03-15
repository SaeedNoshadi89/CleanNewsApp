package com.sn.cleannewsapp.data.remote

import com.sn.cleannewsapp.BuildConfig
import com.sn.cleannewsapp.data.entities.NewsResponse
import com.sn.cleannewsapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    fun getBreakingNews(
        @Query("country") countryCode: String = "us",
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey")apiKey: String = BuildConfig.API_KEY
    ): Flow<Resource<NewsResponse>>

    @GET("everything")
    fun searchNews(
        @Query("q") searchQuery: String,
        @Query("page") pageNumber: Int = 1,
        @Query("apKey") apiKey: String = BuildConfig.API_KEY
    ): Flow<NewsResponse>

}