package com.novitsky.data.repositories

import com.novitsky.data.parsers.NewsRSSParser
import com.novitsky.domain.repository.LentaNetworkRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class LentaNetworkRepositoryImpl : LentaNetworkRepository {
    private val service: LentaService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        service = retrofit.create(LentaService::class.java)
    }

    override fun getNews(category: String, callback: LentaNetworkRepository.Callback, numberOfNews: Int?) {
        service.getNewsRSS(category).enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                callback.onResponse(mutableListOf(), -1)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    val parseResponse = if (numberOfNews != null) {
                        NewsRSSParser().parse(response.body(), numberOfNews)
                    } else {
                        NewsRSSParser().parse(response.body())
                    }

                    callback.onResponse(parseResponse, response.code())
                }
            }
        })
    }

    companion object {
        private const val BASE_URL = "https://lenta.ru/"
    }
}