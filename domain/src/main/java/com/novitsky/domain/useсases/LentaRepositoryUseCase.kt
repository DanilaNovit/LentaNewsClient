package com.novitsky.domain.useсases

import com.novitsky.domain.model.NewsModel

interface LentaRepositoryUseCase {
    fun getCategory(category: String, callback: CallbackCategory)
    fun getCatalog(numberOfNewsInCategory: Int, callback: CallbackCatalog)

    interface CallbackCategory {
        fun onResponse(category: MutableList<NewsModel>)
        fun onFailure(errorMessage: String)
    }

    interface CallbackCatalog {
        fun onResponse(catalog: MutableList<MutableList<NewsModel>>)
        fun onFailure(errorMessage: String)
    }
}