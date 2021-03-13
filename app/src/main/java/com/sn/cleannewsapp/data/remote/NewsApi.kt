package com.sn.cleannewsapp.data.remote

import retrofit2.http.GET

interface NewsApi {
    @GET("news")
    fun getNews():List<*>
}