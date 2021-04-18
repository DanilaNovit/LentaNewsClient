package com.novitsky.domain.useсases

import com.novitsky.domain.model.News
import com.novitsky.domain.model.NewsCategory
import com.novitsky.domain.repository.LentaNetworkRepository

interface LentaRepositoryUseCase {
    fun getCatalog(callback: CallbackCatalog)

    fun getRussianCategory(callback: CallbackCategory)
    fun getWorldCategory(callback: CallbackCategory)
    fun getUssrCategory(callback: CallbackCategory)
    fun getEconomicsCategory(callback: CallbackCategory)
    fun getScienceCategory(callback: CallbackCategory)
    fun getCultureCategory(callback: CallbackCategory)
    fun getSportCategory(callback: CallbackCategory)
    fun getMediaCategory(callback: CallbackCategory)
    fun getStyleCategory(callback: CallbackCategory)
    fun getTravelCategory(callback: CallbackCategory)
    fun getLifeCategory(callback: CallbackCategory)
    fun getRealtyCategory(callback: CallbackCategory)

    interface CallbackCategory {
        fun onResponse(news: MutableList<News>, category: NewsCategory)
        fun onFailure(errorMessage: String)
    }

    interface CallbackCatalog {
        fun onResponse(catalog: MutableMap<NewsCategory, MutableList<News>>)
        fun onFailure(errorMessage: String)
    }
}