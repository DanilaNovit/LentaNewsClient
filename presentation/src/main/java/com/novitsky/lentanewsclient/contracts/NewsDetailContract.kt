package com.novitsky.lentanewsclient.contracts

interface NewsDetailContract {
    interface View {
        fun loadNews(newsID: String)
    }

    interface Presenter {
        fun onViewCreated()
    }
}