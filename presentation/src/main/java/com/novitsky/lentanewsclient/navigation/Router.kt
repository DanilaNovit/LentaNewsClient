package com.novitsky.lentanewsclient.navigation

import com.novitsky.domain.repository.LentaNetworkRepository
import com.novitsky.lentanewsclient.activities.ActivityConfigurationModel

interface Router {
    fun showCatalog()
    fun showCategory(category: LentaNetworkRepository.NewsCategory)
    fun showDetail(url: String)
    fun back()
    fun setConfigHandler(configHandler: ConfigHandler)
    fun configure(config: ActivityConfigurationModel)
}