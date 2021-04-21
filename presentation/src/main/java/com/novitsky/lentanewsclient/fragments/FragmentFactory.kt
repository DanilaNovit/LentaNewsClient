package com.novitsky.lentanewsclient.fragments

import androidx.fragment.app.Fragment
import com.novitsky.lentanewsclient.navigation.Router

interface FragmentFactory {
    fun createCatalog(router: Router): Fragment
    fun createCategory(router: Router, categoryID: Int): Fragment
    fun createDetailNews(id: String): Fragment
}
