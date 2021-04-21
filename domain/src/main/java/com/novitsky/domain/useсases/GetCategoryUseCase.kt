package com.novitsky.domain.useсases

import com.novitsky.domain.model.News

interface GetCategoryUseCase {
    fun get(categoryID: Int, callback: Callback)

    interface Callback {
        fun onResponse(news: MutableList<News>)
        fun onFailure(errorMessage: String)
    }
}
