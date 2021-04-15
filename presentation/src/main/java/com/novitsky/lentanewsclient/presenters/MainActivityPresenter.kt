package com.novitsky.lentanewsclient.presenters

import com.novitsky.lentanewsclient.activities.ActivityConfigurationModel
import com.novitsky.lentanewsclient.navigation.Router
import com.novitsky.lentanewsclient.contracts.MainActivityContract
import com.novitsky.lentanewsclient.navigation.ConfigHandler
import java.lang.ref.WeakReference

class MainActivityPresenter(
        tmpView: MainActivityContract.View,
        private val router: Router
): MainActivityContract.Presenter, ConfigHandler {
    private val view = WeakReference(tmpView)
    private var backButtonClosesActivity = true

    init {
        router.setConfigHandler(this)
    }

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

    override fun configure(config: ActivityConfigurationModel) {
        view.get()?.setTitle(config.title)
        view.get()?.setVisibleBackButton(config.backButtonEnabled)
        this.backButtonClosesActivity = config.backButtonClosesActivity
    }
}
