package com.novitsky.domain.repository

import com.novitsky.domain.model.NewsModel

interface LentaNetworkRepository {
    fun getNews(category: String, callback: Callback, numberOfNews: Int? = null)

    interface Callback {
        fun onResponse(response: MutableList<NewsModel>, responseCode: Int)
    }

    enum class NewsCategory(val value: String) {
        RUSSIA("russia"),
        WORLD("world"),
        USSR("ussr"),
        ECONOMICS("economics"),
        SCIENCE("science"),
        CULTURE("culture"),
        SPORT("sport"),
        MEDIA("media"),
        STYLE("style"),
        TRAVEL("travel"),
        LIFE("life"),
        REALTY("realty"),
    }
}
