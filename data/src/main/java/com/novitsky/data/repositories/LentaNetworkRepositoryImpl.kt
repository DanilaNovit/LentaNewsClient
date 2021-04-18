package com.novitsky.data.repositories

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
    private val service: LentaService
    private var uploadedCategories = 0

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
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
        service.getNewsRSS(getCategoryUrlKey(category)).enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                callback.onResponse(newsContainer, -1)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    if (newsContainer[category] == null) {
                        newsContainer[category] = mutableListOf()
                    }

                    NewsRSSParser().parse(response.body(), newsContainer[category]!!)
                    ++uploadedCategories

                    if (uploadedCategories == NewsCategory.values().size - 1) {
                        getNews(callback)
                    }
                } else {
                    callback.onResponse(newsContainer, response.code())
                }
            }
        })
    }

    private fun getCategoryUrlKey(category: NewsCategory): String {
        return when(category) {
            NewsCategory.RUSSIA -> "russia"
            NewsCategory.WORLD -> "world"
            NewsCategory.USSR -> "ussr"
            NewsCategory.ECONOMICS -> "economics"
            NewsCategory.SCIENCE -> "science"
            NewsCategory.CULTURE -> "culture"
            NewsCategory.SPORT -> "sport"
            NewsCategory.MEDIA -> "media"
            NewsCategory.STYLE -> "style"
            NewsCategory.TRAVEL -> "travel"
            NewsCategory.LIFE -> "life"
            NewsCategory.REALTY -> "realty"
        }
    }

    companion object {
        private const val BASE_URL = "https://lenta.ru/"
        private var newsContainer = mutableMapOf<NewsCategory, MutableList<News>>()
    }
}