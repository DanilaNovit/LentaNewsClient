package com.novitsky.lentanewsclient.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.novitsky.lentanewsclient.R
import com.novitsky.lentanewsclient.contracts.NewsDetailContract

class NewsDetailFragment : Fragment, NewsDetailContract.View {
    private lateinit var webView: WebView
    private lateinit var presenter: NewsDetailContract.Presenter

    constructor(): super()

    constructor(presenter: NewsDetailContract.Presenter) {
        this.presenter = presenter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news_detail, container, false)
        webView = view.findViewById(R.id.news_detail_web_view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun javaScriptEnabled(enabled: Boolean) {
        webView.settings.javaScriptEnabled = enabled
    }

    override fun setWebViewClient(client: WebViewClient) {
        webView.webViewClient = client
    }

    override fun loadURL(url: String) {
        webView.loadUrl(url)
    }
}