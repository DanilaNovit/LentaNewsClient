package com.novitsky.domain.useсases

import com.novitsky.domain.model.NewsModel
import com.novitsky.domain.repository.LentaNetworkRepository

interface LentaRepositoryUseCase {
    fun getCategory(category: LentaNetworkRepository.NewsCategory, callback: CallbackCategory)
    fun getCatalog(numberOfNewsInCategory: Int, callback: CallbackCatalog)

    interface CallbackCategory {
        fun onResponse(news: MutableList<NewsModel>,
                       category: LentaNetworkRepository.NewsCategory)
        fun onFailure(errorMessage: String)
    }

    interface CallbackCatalog {
        fun onResponse(catalog: MutableMap<LentaNetworkRepository.NewsCategory,
                MutableList<NewsModel>>)
        fun onFailure(errorMessage: String)
    }
}