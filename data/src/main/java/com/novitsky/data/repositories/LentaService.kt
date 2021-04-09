package com.novitsky.data.repositories

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface LentaService {
    @GET("/rss/news/{category}")
    fun getNewsRSS(@Path("category") category: String): Call<String>
}