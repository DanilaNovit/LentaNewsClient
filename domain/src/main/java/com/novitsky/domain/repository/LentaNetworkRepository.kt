package com.novitsky.domain.repository

import com.novitsky.domain.model.News

interface LentaNetworkRepository {
    fun getNews(callback: Callback)
    fun updateNews(callback: Callback)

    interface Callback {
        fun onResponse(response: MutableMap<Int, MutableList<News>>, responseCode: Int)
    }
}
