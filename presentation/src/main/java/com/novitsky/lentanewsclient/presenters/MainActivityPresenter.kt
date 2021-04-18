package com.novitsky.lentanewsclient.presenters

import com.novitsky.lentanewsclient.navigation.Router
import com.novitsky.lentanewsclient.contracts.MainActivityContract
import java.lang.ref.WeakReference

class MainActivityPresenter(
        tmpView: MainActivityContract.View,
        private val router: Router
): MainActivityContract.Presenter {
    private val view = WeakReference(tmpView)
    private var backButtonClosesActivity = true

    override fun onCreateActivity() {
        router.showCatalog()
    }

    override fun onClickBackButton() {
        if (backButtonClosesActivity) {
            view.get()?.finish()
        } else {
            router.back()
        }
    }

    override fun onBackStackChanged(size: Int) {
        backButtonClosesActivity = size == 1
        view.get()?.setVisibleBackButton(size != 1)
    }
}
