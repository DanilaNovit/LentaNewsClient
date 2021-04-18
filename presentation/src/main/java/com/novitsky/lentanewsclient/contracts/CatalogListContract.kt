package com.novitsky.lentanewsclient.contracts

import com.novitsky.domain.model.News
import com.novitsky.domain.model.NewsCategory

interface CatalogListContract {
    interface View {
        fun updateData(catalogMap: MutableMap<NewsCategory, MutableList<News>>)
        fun showError(message: String)
    }

    interface Presenter {
        fun onViewCreated()
        fun onCategoryItemClicked(category: NewsCategory)
        fun onNewsItemClicked(item: News)
    }
}