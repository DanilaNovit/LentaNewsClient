package com.novitsky.domain.useсases

import com.novitsky.domain.model.News
import com.novitsky.domain.repository.LentaNetworkRepository

interface LentaRepositoryUseCase {
    fun getCategory(category: LentaNetworkRepository.NewsCategory, callback: CallbackCategory)
    fun getCatalog(numberOfNewsInCategory: Int, callback: CallbackCatalog)

    interface CallbackCategory {
        fun onResponse(news: MutableList<News>,
                       category: LentaNetworkRepository.NewsCategory)
        fun onFailure(errorMessage: String)
    }

    interface CallbackCatalog {
        fun onResponse(catalog: MutableMap<LentaNetworkRepository.NewsCategory,
                MutableList<News>>)
        fun onFailure(errorMessage: String)
    }
}