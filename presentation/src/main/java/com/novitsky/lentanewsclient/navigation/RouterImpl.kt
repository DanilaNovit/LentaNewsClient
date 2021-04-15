package com.novitsky.lentanewsclient.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.novitsky.domain.repository.LentaNetworkRepository
import com.novitsky.lentanewsclient.activities.ActivityConfigurationModel
import com.novitsky.lentanewsclient.fragments.FragmentFactory
import com.novitsky.lentanewsclient.fragments.FragmentFactoryImpl

class RouterImpl(
    private val manager: FragmentManager,
    private val containerViewId: Int
): Router {
    private val fragmentFactory: FragmentFactory = FragmentFactoryImpl()
    private var configHandler: ConfigHandler? = null

    override fun showCatalog() {
        replace(fragmentFactory.createCatalog(this))
    }

    override fun showCategory(category: LentaNetworkRepository.NewsCategory) {
        replace(fragmentFactory.createCategory(this, category))
    }

    override fun showDetail(url: String) {
        replace(fragmentFactory.createDetailNews(this, url))
    }

    override fun back() {
        manager.popBackStack()
    }

    override fun setConfigHandler(configHandler: ConfigHandler) {
        this.configHandler = configHandler
    }

    override fun configure(config: ActivityConfigurationModel) {
        configHandler?.configure(config)
    }

    private fun replace(fragment: Fragment) {
        val transaction = manager.beginTransaction()
        transaction.replace(containerViewId, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
