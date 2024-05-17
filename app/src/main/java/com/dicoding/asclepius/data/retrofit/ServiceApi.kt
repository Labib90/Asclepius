package com.dicoding.asclepius.data.retrofit

import com.dicoding.asclepius.data.response.ResponseNew
import com.dicoding.asclepius.utils.NEWS_CATEGORY
import com.dicoding.asclepius.utils.NEWS_LANGUAGE
import com.dicoding.asclepius.utils.NEWS_QUERY
import com.yalantis.ucrop.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {
    @GET("top-headlines")
    fun getNews(
        @Query("q") query: String = NEWS_QUERY,
        @Query("category") category: String = NEWS_CATEGORY,
        @Query("language") language: String = NEWS_LANGUAGE,
        @Query("apiKey") apiKey: String = com.dicoding.asclepius.BuildConfig.API_KEY
    ): Call<ResponseNew>
}