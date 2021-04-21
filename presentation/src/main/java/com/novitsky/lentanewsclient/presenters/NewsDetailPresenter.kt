package com.novitsky.lentanewsclient.presenters

import com.novitsky.lentanewsclient.contracts.NewsDetailContract
import java.lang.ref.WeakReference

class NewsDetailPresenter(
        private val newsID: String
): NewsDetailContract.Presenter {
    private lateinit var view: WeakReference<NewsDetailContract.View>

    fun setView(view: NewsDetailContract.View) {
        this.view = WeakReference(view)
    }

    override fun onViewCreated() {
        view.get()?.loadNews(newsID)
    }
}