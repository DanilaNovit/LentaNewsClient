package com.novitsky.lentanewsclient.presenters

import android.webkit.WebView
import android.webkit.WebViewClient
import com.novitsky.lentanewsclient.activities.ActivityConfigurationModel
import com.novitsky.lentanewsclient.contracts.NewsDetailContract
import com.novitsky.lentanewsclient.navigation.Router
import java.lang.ref.WeakReference

class NewsDetailPresenter(
        private val router: Router,
        private val url: String
): NewsDetailContract.Presenter {
    private lateinit var view: WeakReference<NewsDetailContract.View>

    fun setView(view: NewsDetailContract.View) {
        this.view = WeakReference(view)
    }

    override fun onViewCreated() {
        setConfig()

        view.get()?.javaScriptEnabled(true)
        view.get()?.setWebViewClient(object: WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                return !(url != null && url.contains("lenta.ru"))
            }
        })
        view.get()?.loadURL(url)
    }

    private fun setConfig() {
        val context = view.get()?.getContext()
        val res = context?.resources
        val title = res?.getString(res.getIdentifier("app_name", "string", context.packageName))

        router.configure(ActivityConfigurationModel(
                title = title!!,
                backButtonEnabled = true,
                backButtonClosesActivity = false
        ))
    }
}