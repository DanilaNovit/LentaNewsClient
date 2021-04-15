package com.novitsky.lentanewsclient.contracts

import android.content.Context
import android.webkit.WebViewClient

interface NewsDetailContract {
    interface View {
        fun javaScriptEnabled(enabled: Boolean)
        fun setWebViewClient(client: WebViewClient)
        fun loadURL(url: String)
        fun getContext(): Context?
    }

    interface Presenter {
        fun onViewCreated()
    }
}