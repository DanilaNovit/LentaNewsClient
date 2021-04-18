package com.novitsky.lentanewsclient.fragments

import androidx.fragment.app.Fragment
import com.novitsky.domain.model.NewsCategory
import com.novitsky.lentanewsclient.navigation.Router

interface FragmentFactory {
    fun createCatalog(router: Router): Fragment
    fun createCategory(router: Router, category: NewsCategory): Fragment
    fun createDetailNews( url: String): Fragment
}