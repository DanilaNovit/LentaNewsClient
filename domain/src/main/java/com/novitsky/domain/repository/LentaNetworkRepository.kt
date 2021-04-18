package com.novitsky.domain.repository

import com.novitsky.domain.model.News
import com.novitsky.domain.model.NewsCategory

interface LentaNetworkRepository {
    fun getNews(callback: Callback)
    fun updateNews(callback: Callback)

    interface Callback {
        fun onResponse(response: MutableMap<NewsCategory, MutableList<News>>, responseCode: Int)
    }

}
