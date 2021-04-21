package com.novitsky.data.repositories

import com.novitsky.data.mappers.NewsMapper
import com.novitsky.data.parsers.NewsRSSParser
import com.novitsky.domain.model.News
import com.novitsky.domain.model.NewsCategory
import com.novitsky.domain.repository.LentaNetworkRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class LentaNetworkRepositoryImpl : LentaNetworkRepository {
    private var newsContainer = mutableMapOf<Int, MutableList<News>>()
    private val baseURL = "https://lenta.ru/"

    private val service: LentaService
    private var uploadedCategories = 0

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        service = retrofit.create(LentaService::class.java)
    }

    override fun getNews(callback: LentaNetworkRepository.Callback) {
        if (newsContainer.isEmpty()) {
            updateNews(callback)
        } else {
            callback.onResponse(newsContainer, 200)
        }
    }

    override fun updateNews(callback: LentaNetworkRepository.Callback) {
        uploadedCategories = 0

        NewsCategory.values().forEach {
            updateCategory(it, callback)
        }
    }

    private fun updateCategory(category: NewsCategory, callback: LentaNetworkRepository.Callback) {
        service.getNewsRSS(NewsMapper().getUrlKeyByCategoryNews(category)).enqueue(
                object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                callback.onResponse(newsContainer, -1)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    val categoryID = NewsMapper().getIdByCategoryNews(category)

                    if (newsContainer[categoryID] == null) {
                        newsContainer[categoryID] = mutableListOf()
                    }

                    NewsRSSParser().parse(response.body(), newsContainer[categoryID]!!)
                    ++uploadedCategories

                    if (uploadedCategories == NewsCategory.values().size) {
                        getNews(callback)
                    }
                } else {
                    callback.onResponse(newsContainer, response.code())
                }
            }
        })
    }
}