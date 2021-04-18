package com.novitsky.domain.useсases

import com.novitsky.domain.model.News
import com.novitsky.domain.model.NewsCategory
import com.novitsky.domain.repository.LentaNetworkRepository

interface LentaRepositoryUseCase {
    fun getCategory(category: NewsCategory, callback: CallbackCategory)
    fun getCatalog(callback: CallbackCatalog)

    interface CallbackCategory {
        fun onResponse(news: MutableList<News>, category: NewsCategory)
        fun onFailure(errorMessage: String)
    }

    interface CallbackCatalog {
        fun onResponse(catalog: MutableMap<NewsCategory, MutableList<News>>)
        fun onFailure(errorMessage: String)
    }
}