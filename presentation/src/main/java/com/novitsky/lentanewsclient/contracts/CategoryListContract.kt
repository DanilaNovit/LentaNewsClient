package com.novitsky.lentanewsclient.contracts

import com.novitsky.domain.model.News

interface CategoryListContract {
    interface View {
        fun updateData(newsList: MutableList<News>?)
        fun showError(message: String)
        fun updateTitle(title: String)
    }

    interface Presenter {
        fun onViewCreated()
        fun onClickItemNews(item: News)
        fun onFragmentLastInBackStack()
    }
}