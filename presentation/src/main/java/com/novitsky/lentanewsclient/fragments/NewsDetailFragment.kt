package com.novitsky.lentanewsclient.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.FragmentManager
import com.novitsky.lentanewsclient.R

class NewsDetailFragment : ActionBarFragment, FragmentManager.OnBackStackChangedListener {
    private lateinit var webView: WebView
    private lateinit var url: String

    constructor(): super()

    constructor(url: String) {
        this.url = url
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.supportFragmentManager?.addOnBackStackChangedListener(this)

        val view = inflater.inflate(R.layout.fragment_news_detail, container, false)

        webView = view.findViewById(R.id.news_detail_web_view)

        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object: WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                return !(url != null && url.contains("lenta.ru"))
            }
        }
        webView.loadUrl(url)

        return view
    }

    override fun onBackStackChanged() {
        val manager = activity?.supportFragmentManager

        if (manager?.fragments?.last() == this) {
            updateTitle(getString(R.string.app_name))
        }
    }
}