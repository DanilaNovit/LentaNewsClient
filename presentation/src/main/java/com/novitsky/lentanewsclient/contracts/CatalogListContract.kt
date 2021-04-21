package com.novitsky.lentanewsclient.contracts

import com.novitsky.domain.model.News

interface CatalogListContract {
    interface View {
        fun updateData(items: MutableList<Any>)
        fun showError(message: String)
    }

    interface Presenter {
        fun onViewCreated()
        fun onCategoryItemClicked(categoryID: Int)
        fun onNewsItemClicked(item: News)
    }
}