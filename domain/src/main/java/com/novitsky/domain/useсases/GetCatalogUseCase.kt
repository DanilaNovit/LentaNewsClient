package com.novitsky.domain.useсases

import com.novitsky.domain.model.News

interface GetCatalogUseCase {
    fun get(callback: Callback)

    interface Callback {
        fun onResponse(catalog: MutableMap<Int, MutableList<News>>)
        fun onFailure(errorMessage: String)
    }
}