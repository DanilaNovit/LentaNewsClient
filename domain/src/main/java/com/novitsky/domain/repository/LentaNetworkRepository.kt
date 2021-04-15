package com.novitsky.domain.repository

import com.novitsky.domain.model.News

interface LentaNetworkRepository {
    fun getNews(category: NewsCategory, callback: Callback,
                container: MutableList<News>, numberOfNews: Int? = null)

    interface Callback {
        fun onResponse(response: MutableList<News>, responseCode: Int)
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
