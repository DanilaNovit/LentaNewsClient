package com.novitsky.lentanewsclient.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.FragmentManager
import com.novitsky.lentanewsclient.R
import com.novitsky.lentanewsclient.contracts.NewsDetailContract

class NewsDetailFragment: ActionBarFragment(),
		FragmentManager.OnBackStackChangedListener, NewsDetailContract.View {
	private lateinit var webView: WebView
	private lateinit var presenter: NewsDetailContract.Presenter

	fun setArguments(presenter: NewsDetailContract.Presenter) {
		this.presenter = presenter
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
		webView.webViewClient = object : WebViewClient() {
			override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
				return !(url != null && url.contains("lenta.ru"))
			}
		}

		return view
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		presenter.onViewCreated()
	}

	override fun onBackStackChanged() {
		val manager = activity?.supportFragmentManager

		if (manager?.fragments?.last() == this) {
			updateTitle(getString(R.string.app_name))
		}
	}

	override fun loadNews(newsID: String) {
		webView.loadUrl(newsID)
	}
}