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

class NewsDetailFragment : Fragment() {
    private var webView: WebView? = null
    private var url: String? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news_detail, container, false)

        webView = view.findViewById(R.id.news_detail_web_view)

        if (webView != null) {
            webView!!.settings.javaScriptEnabled = true
            webView!!.loadUrl(url.toString())
            webView!!.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    url: String?
                ): Boolean {
                    return false
                }
            }
        }

        return view
    }

    fun setURL(url: String?) {
        this.url = url
    }
}