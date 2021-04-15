package com.novitsky.lentanewsclient.fragments

import androidx.fragment.app.Fragment
import com.novitsky.domain.repository.LentaNetworkRepository
import com.novitsky.lentanewsclient.navigation.Router

interface FragmentFactory {
    fun createCatalog(router: Router): Fragment
    fun createCategory(router: Router, category: LentaNetworkRepository.NewsCategory): Fragment
    fun createDetailNews(router: Router, url: String): Fragment
}