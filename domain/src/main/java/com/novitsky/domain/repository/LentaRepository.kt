package com.novitsky.domain.repository

import com.novitsky.domain.model.NewsModel

interface LentaRepository {
    fun getNews(category: String, callback: Callback, numberOfNews: Int? = null)

    interface Callback {
        fun onResponse(response: MutableList<NewsModel>, responseCode: Int)
    }

//    companion object {
//        const val NUMBER_OF_CATEGORIES = 12
//        const val RUSSIA_CATEGORY = "russia"
//        const val WORLD_CATEGORY = "world"
//        const val USSR_CATEGORY = "ussr"
//        const val ECONOMICS_CATEGORY = "economics"
//        const val SCIENCE_CATEGORY = "science"
//        const val CULTURE_CATEGORY = "culture"
//        const val SPORT_CATEGORY = "sport"
//        const val MEDIA_CATEGORY = "media"
//        const val STYLE_CATEGORY = "style"
//        const val TRAVEL_CATEGORY = "travel"
//        const val LIFE_CATEGORY = "life"
//        const val REALTY_CATEGORY = "realty"
//        var a = NewsCategory.RUSSIA
//        val v =NewsCategory.
//    }

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
